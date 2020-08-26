package com.boiechko.controller;

import com.boiechko.entity.Order;
import com.boiechko.entity.OrderProduct;
import com.boiechko.entity.Product;
import com.boiechko.service.implementations.OrderProductServiceImpl;
import com.boiechko.service.implementations.OrderServiceImpl;
import com.boiechko.service.interfaces.OrderService;
import com.boiechko.utils.ConvertDateUtil;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {

            int idPerson = (int) session.getAttribute("userId");

            final String[] selectedItems = request.getParameterValues("json[]");
            final int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
            final String dateOrder = request.getParameter("dateOrder");

            List<Integer> selectedItemsIntegers = new ArrayList<>();
            for (String selectedItem : selectedItems) selectedItemsIntegers.add(Integer.parseInt(selectedItem));

            Order order = new Order(idPerson, totalPrice, ConvertDateUtil.convertDate(dateOrder));

            if (orderService.add(order)) {

                List<Product> shoppingBag = (List<Product>) session.getAttribute("shoppingBag");

                int idOrder = orderService.getLastId();

                for (int i = 0; i < shoppingBag.size(); i++) {

                    OrderProduct orderProduct = new OrderProduct(idOrder, shoppingBag.get(i).getIdProduct(), selectedItemsIntegers.get(i));

                    if (!orderProductService.add(orderProduct)) {
                        response.sendError(500);
                    }


                }

                shoppingBag.clear();
                session.setAttribute("shoppingBag", shoppingBag);

            } else {
                response.sendError(500);
            }
        } catch (Error error) {
            error.printStackTrace();
            response.sendError(500);
        }

    }
}
