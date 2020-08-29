package com.boiechko.controller.Profile.Address;

import com.boiechko.service.implementations.AddressServiceImpl;
import com.boiechko.service.interfaces.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userProfile/userAddresses/deleteAddress/*")
public class ProfileAddressDeleteServlet extends HttpServlet {

    private final AddressService addressService = new AddressServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String[] elements = request.getRequestURI().split("/");

            final int addressId = Integer.parseInt(elements[elements.length - 1]);

            if (addressService.deleteAddress(addressId))
                response.sendRedirect("/userProfile/userAddresses");
            else
                response.sendError(500);

        } catch (Exception exception) {
            exception.printStackTrace();
            response.sendError(500);
        }

    }
}
