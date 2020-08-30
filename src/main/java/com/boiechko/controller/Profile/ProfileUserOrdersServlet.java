package com.boiechko.controller.Profile;

import com.boiechko.entity.Order;
import com.boiechko.entity.Person;
import com.boiechko.entity.Product;
import com.boiechko.service.implementations.AddressServiceImpl;
import com.boiechko.service.implementations.OrderServiceImpl;
import com.boiechko.service.interfaces.AddressService;
import com.boiechko.service.interfaces.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/userProfile/userOrders")
public class ProfileUserOrdersServlet extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();
    private final AddressService addressService = new AddressServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Person person = (Person) request.getSession().getAttribute("person");
        final String idOrderString = request.getParameter("idOrder");

        if (idOrderString != null) {

            final Map.Entry<Order, List<Product>> entry = orderService.getOrderAndHisProducts(person.getIdPerson(), idOrderString);

            request.setAttribute("order", entry.getKey());
            request.setAttribute("products", entry.getValue());
            request.setAttribute("address", addressService.getAddressById(entry.getKey().getIdAddress()));

            request.getRequestDispatcher("/jsp-pages/Profile/Orders/orderItem.jsp").forward(request, response);

        } else {

            request.setAttribute("productsByOrder", orderService.getAllOrdersAndTheirProducts(person.getIdPerson()));

            request.getRequestDispatcher("/jsp-pages/Profile/Orders/orders.jsp").forward(request, response);

        }
    }
}
