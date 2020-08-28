package com.boiechko.controller.Profile;

import com.boiechko.entity.Address;
import com.boiechko.entity.Order;
import com.boiechko.entity.Person;
import com.boiechko.entity.Product;
import com.boiechko.service.implementations.AddressServiceImpl;
import com.boiechko.service.implementations.OrderServiceImpl;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.AddressService;
import com.boiechko.service.interfaces.OrderService;
import com.boiechko.service.interfaces.PersonService;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final OrderService orderService = new OrderServiceImpl();
        final AddressService addressService = new AddressServiceImpl();
        final PersonService personService = new PersonServiceImpl();

        final int idUser = (int) session.getAttribute("userId");

        final String idOrderString = request.getParameter("idOrder");

        if (idOrderString != null) {

            Map<Order, List<Product>> map = orderService.getOrderAndHisProducts(idUser, Integer.parseInt(idOrderString));

            for (Map.Entry<Order, List<Product>> entry : map.entrySet()) {

                Order order = entry.getKey();
                List<Product> products = entry.getValue();

                request.setAttribute("order", order);
                request.setAttribute("products", products);

                Address address = addressService.getById(order.getIdAddress());

                request.setAttribute("address", address);

                Person person = personService.getById(idUser);

                request.setAttribute("person", person);

            }

            request.getRequestDispatcher("/jsp-pages/Profile/Orders/orderItem.jsp").forward(request, response);

        } else {

            Map<Order, List<Product>> map = orderService.getAllOrdersAndTheirProducts(idUser);

            request.setAttribute("productsByOrder", map);

            request.getRequestDispatcher("/jsp-pages/Profile/Orders/orders.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
