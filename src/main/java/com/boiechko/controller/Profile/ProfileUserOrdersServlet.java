package com.boiechko.controller.Profile;

import com.boiechko.entity.Address;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/userProfile/userOrders")
public class ProfileUserOrdersServlet extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();
    private final AddressService addressService = new AddressServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final Person person = (Person) session.getAttribute("person");
        final String idOrderString = request.getParameter("idOrder");

        if (idOrderString != null) {

            Map<Order, List<Product>> map = orderService.getOrderAndHisProducts(person.getIdPerson(), Integer.parseInt(idOrderString));

            for (Map.Entry<Order, List<Product>> entry : map.entrySet()) {

                final Order order = entry.getKey();
                final List<Product> products = entry.getValue();
                final Address address = addressService.getAddressById(order.getIdAddress());

                request.setAttribute("order", order);
                request.setAttribute("products", products);
                request.setAttribute("address", address);

            }
            request.getRequestDispatcher("/jsp-pages/Profile/Orders/orderItem.jsp").forward(request, response);

        } else {

            request.setAttribute("productsByOrder", orderService.getAllOrdersAndTheirProducts(person.getIdPerson()));

            request.getRequestDispatcher("/jsp-pages/Profile/Orders/orders.jsp").forward(request, response);

        }
    }
}
