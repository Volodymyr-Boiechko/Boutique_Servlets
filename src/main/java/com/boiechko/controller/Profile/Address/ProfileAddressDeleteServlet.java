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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String path = request.getRequestURI();
            String[] elements = path.split("/");

            int addressId = Integer.parseInt(elements[elements.length - 1]);

            AddressService addressService = new AddressServiceImpl();

            boolean success = addressService.delete(addressId);

            if (success)
                response.sendRedirect("/userProfile/userAddresses");
            else
                response.sendError(500);

        } catch (Exception exception) {
            exception.printStackTrace();
            response.sendError(500);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
