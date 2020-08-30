package com.boiechko.controller.Profile;

import com.boiechko.entity.Address;
import com.boiechko.entity.Person;
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

@WebServlet("/userProfile/userAddresses/*")
public class ProfileAddressesServlet extends HttpServlet {

    private final AddressService addressService = new AddressServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        final Person person = (Person) session.getAttribute("person");

        String[] path = request.getRequestURI().split("/");

        if (path.length == 3 && path[2].contains("userAddresses")) {

            final List<Address> addresses = addressService.getAddressesOfUser(person.getIdPerson());

            request.setAttribute("canDelete", addressService.canDeleteAddress(addresses));
            request.setAttribute("addresses", addresses);

            request.getRequestDispatcher("/jsp-pages/Profile/Address/addresses.jsp").forward(request, response);

        } else if (path.length == 4 && path[3].contains("editAddress")) {

            final int idAddress = Integer.parseInt(request.getParameter("idAddress"));
            final Address address = addressService.getAddressById(idAddress);

            final boolean canDelete = orderService.checkIfAddressHasOrder(idAddress);

            request.setAttribute("canDelete", canDelete);
            request.setAttribute("address", address);

            request.getRequestDispatcher("/jsp-pages/Profile/Address/editAddress.jsp").forward(request, response);

        } else if (path.length == 4 && path[3].contains("addAddress")) {

            request.getRequestDispatcher("/jsp-pages/Profile/Address/addAddress.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Person person = (Person) request.getSession().getAttribute("person");

        final String country = request.getParameter("country");
        final String city = request.getParameter("city");
        final String street = request.getParameter("street");
        final String postCode = request.getParameter("postCode");

        final Address address = new Address(person.getIdPerson(), country, city, street, postCode);

        if (!addressService.addAddress(address)) response.sendError(500);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int idAddress = Integer.parseInt(request.getParameter("id"));
        final String country = request.getParameter("country");
        final String city = request.getParameter("city");
        final String street = request.getParameter("street");
        final String postCode = request.getParameter("postCode");

        final Address address = addressService.getAddressById(idAddress);

        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setPostCode(postCode);

        if (!addressService.updateAddress(address)) response.sendError(500);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int idAddress = Integer.parseInt(request.getParameter("idAddress"));

        if (!addressService.deleteAddress(idAddress)) response.sendError(500);

    }
}
