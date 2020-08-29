package com.boiechko.controller;

import com.boiechko.entity.Order;
import com.boiechko.entity.OrderProduct;
import com.boiechko.entity.Product;
import com.boiechko.service.implementations.OrderProductServiceImpl;
import com.boiechko.service.implementations.OrderServiceImpl;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.OrderService;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.ConvertDateUtil;
import com.boiechko.utils.Mail.JavaMailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/makeOrder")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();
    private final OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();
    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {

            //todo Замінити на атрибут Person
            int idPerson = (int) session.getAttribute("userId");

            final String[] selectedItems = request.getParameterValues("json[]");
            final int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
            final int idAddress = Integer.parseInt(request.getParameter("idAddress"));
            final String dateOrder = request.getParameter("dateOrder");

            List<Integer> selectedItemsIntegers = new ArrayList<>();
            for (String selectedItem : selectedItems) selectedItemsIntegers.add(Integer.parseInt(selectedItem));

            Order order = new Order(idPerson, idAddress, totalPrice, ConvertDateUtil.convertDate(dateOrder));

            if (orderService.addOrder(order)) {

                List<Product> shoppingBag = (List<Product>) session.getAttribute("shoppingBag");

                int idOrder = orderService.getLastId();

                for (int i = 0; i < shoppingBag.size(); i++) {

                    OrderProduct orderProduct = new OrderProduct(idOrder, shoppingBag.get(i).getIdProduct(), selectedItemsIntegers.get(i));
                    shoppingBag.get(i).setQuantity(selectedItemsIntegers.get(i));

                    if (!orderProductService.addOrderProduct(orderProduct)) {
                        response.sendError(500);
                    }

                }

                order.setIdOrder(idOrder);
                JavaMailUtil javaMailUtil = new JavaMailUtil("orderDetail", order, shoppingBag);
                javaMailUtil.sendMail(personService.getPersonById(idPerson).getEmail());

                session.setAttribute("shoppingBag", new ArrayList<Product>());

            } else {
                response.sendError(500);
            }
        } catch (Error error) {
            error.printStackTrace();
            response.sendError(500);
        }

    }
}
