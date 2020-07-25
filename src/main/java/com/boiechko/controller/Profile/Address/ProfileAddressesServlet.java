package com.boiechko.controller.Profile.Address;

import com.boiechko.entity.Address;
import com.boiechko.entity.Person;
import com.boiechko.service.implementations.AddressServiceImpl;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.AddressService;
import com.boiechko.service.interfaces.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/userProfile/userAddresses")
public class ProfileAddressesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AddressService addressService = new AddressServiceImpl();
        PersonService personService = new PersonServiceImpl();

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        List<Address> addresses = addressService.getAddressesOfUser(userId);
        Person person = personService.getById(userId);

        request.setAttribute("addresses", addresses);
        request.setAttribute("person", person);

        request.getRequestDispatcher("/jsp-pages/Profile/Address/addresses.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
