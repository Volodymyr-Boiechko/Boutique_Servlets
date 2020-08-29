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

@WebServlet("/userProfile/userAddresses/editAddress/*")
public class ProfileAddressEditServlet extends HttpServlet {

    private final AddressService addressService = new AddressServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String[] path = request.getRequestURI().split("/");

            int idAddress = Integer.parseInt(path[path.length - 1]);
            Address address = addressService.getAddressById(idAddress);

            request.setAttribute("address", address);

        } catch (Exception exception) {
            exception.printStackTrace();
            response.sendError(500);
        }

        request.getRequestDispatcher("/jsp-pages/Profile/Address/editAddress.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int idAddress = Integer.parseInt(request.getParameter("id"));

        final String country = request.getParameter("country");
        final String city = request.getParameter("city");
        final String street = request.getParameter("street");
        final String postCode = request.getParameter("postCode");

        Address address = addressService.getAddressById(idAddress);

        if (address.getCountry() != null) {

            address.setCountry(country);
            address.setCity(city);
            address.setStreet(street);
            address.setPostCode(postCode);

            if (addressService.updateAddress(address)) {

                request.getSession().removeAttribute("address");

            } else {
                response.sendError(500);
            }
        } else {
            response.sendError(500);
        }
    }
}
