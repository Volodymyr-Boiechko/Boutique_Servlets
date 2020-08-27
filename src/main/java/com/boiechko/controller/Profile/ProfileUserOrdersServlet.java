package com.boiechko.controller.Profile;

import com.boiechko.entity.Order;
import com.boiechko.entity.Product;
import com.boiechko.service.implementations.OrderServiceImpl;
import com.boiechko.service.interfaces.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/userProfile/userOrders")
public class ProfileUserOrdersServlet extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final int idUser = (int) session.getAttribute("userId");

        final String idOrderString = request.getParameter("idOrder");

        if (idOrderString != null) {

            request.setAttribute("orderAndProducts", orderService.getOrderAndHisProducts(idUser, Integer.parseInt(idOrderString)));

        } else {

            Map<Order, List<Product>> map = orderService.getAllOrdersAndTheirProducts(idUser);

            request.setAttribute("productsByOrder", map);

        }

        request.getRequestDispatcher("/jsp-pages/Profile/Orders/orders.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
