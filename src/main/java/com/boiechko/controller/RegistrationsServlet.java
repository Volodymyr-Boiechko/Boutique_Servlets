package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.ConvertDateUtil;
import com.boiechko.utils.HashingPassword.HashPasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("jsp-pages/registration.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String date = request.getParameter("date");
        String email = request.getParameter("email");

        PersonService personService = new PersonServiceImpl();
        Person person = personService.getPersonByCredentials(username);

        if (person.getUsername() == null) {

            String hashedPassword = HashPasswordUtil.hashPassword(password);

            person = new Person(username, hashedPassword, ConvertDateUtil.convertDate(date), email);

            if (personService.add(person)) {

                HttpSession session = request.getSession();
                session.setAttribute("username", username);

            } else {
                response.sendError(500);
            }

        } else {
            response.sendError(403);
        }
    }
}
