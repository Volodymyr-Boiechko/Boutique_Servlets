package com.boiechko.controller;

import com.boiechko.entity.Order;
import com.boiechko.entity.Person;
import com.boiechko.entity.Product;
import com.boiechko.service.implementations.JavaMailService;
import com.boiechko.service.implementations.OrderProductServiceImpl;
import com.boiechko.service.implementations.OrderServiceImpl;
import com.boiechko.service.interfaces.OrderService;
import com.boiechko.utils.ConvertDateUtil;
import com.boiechko.utils.Mail.JavaMailUtil;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final HttpSession session = request.getSession();

        final Person person = (Person) session.getAttribute("person");

        final String[] arrayOfProductsQuantities = request.getParameterValues("json[]");
        final int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
        final int idAddress = Integer.parseInt(request.getParameter("idAddress"));
        final String dateOrder = request.getParameter("dateOrder");

        final Order order = new Order(person.getIdPerson(), idAddress, totalPrice, ConvertDateUtil.convertDate(dateOrder));
        final List<Product> shoppingBag = (List<Product>) session.getAttribute("shoppingBag");

        if (orderService.addOrder(order)) {

            final int idOrder = orderService.getIdOfLastAddedOrder();
            order.setIdOrder(idOrder);

            if (!orderProductService.addOrderProduct(idOrder, arrayOfProductsQuantities, shoppingBag)) {
                response.sendError(500);
            }

            JavaMailService.sendOrderDetailsEmail(person.getEmail(), "orderDetail", order, shoppingBag);

            session.setAttribute("shoppingBag", new ArrayList<Product>());

        } else {
            response.sendError(500);
        }

    }
}
