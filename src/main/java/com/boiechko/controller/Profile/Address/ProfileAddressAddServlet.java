package com.boiechko.controller.Profile.Address;

import com.boiechko.entity.Address;
import com.boiechko.service.implementations.AddressServiceImpl;
import com.boiechko.service.interfaces.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userProfile/userAddresses/addAddress")
public class ProfileAddressAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp-pages/Profile/Address/addAddress.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int userId = (int) request.getSession().getAttribute("userId");

        final String country = request.getParameter("country");
        final String city = request.getParameter("city");
        final String street = request.getParameter("street");
        final String postCode = request.getParameter("postCode");

        AddressService addressService = new AddressServiceImpl();

        Address address = new Address(userId, country, city, street, postCode);

        if (!addressService.add(address)) {
            response.sendError(500);
        }
    }
}
