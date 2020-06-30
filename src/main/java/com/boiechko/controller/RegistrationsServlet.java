package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.ConvertDateUtil;
import com.boiechko.utils.HashingPassword.HashPasswordUtil;
import com.boiechko.utils.JavaMailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration/*")
public class RegistrationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getRequestURI();
        String[] pathParts = pathInfo.split("/");

        if (pathParts.length != 2 && pathParts[2].length() != 36) {

            String activationCode = pathParts[2];

            PersonService personService = new PersonServiceImpl();
            Person person = personService.getPersonByCredentials("activationCode", activationCode);;

            if (person.getUsername() != null) {

                person.setActivationCode(null);

                if (personService.update(person))
                    response.sendRedirect("/login");

            } else {
                response.sendError(503);
            }

        } else {
            request.getRequestDispatcher("/jsp-pages/registration.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final String date = request.getParameter("date");
        final String email = request.getParameter("email");

        PersonService personService = new PersonServiceImpl();
        Person person = personService.getPersonByCredentials("username", username);

        if (person.getUsername() == null) {

            String hashedPassword = HashPasswordUtil.hashPassword(password);

            person = new Person(username, hashedPassword, ConvertDateUtil.convertDate(date), email);

            if (personService.add(person)) {

                JavaMailUtil javaMailUtil = new JavaMailUtil("confirmRegistration");
                javaMailUtil.sendMail(email);

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
