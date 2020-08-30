package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.ConvertDateUtil;
import com.boiechko.utils.HashingPassword.HashPasswordUtil;
import com.boiechko.utils.Mail.JavaMailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationsServlet extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String activationCode = request.getParameter("activationCode");

        if (activationCode != null) {

            final Person person = personService.getPersonByCredentials("activationCode", activationCode);
            person.setActivationCode(null);

            if (personService.updatePerson(person))
                response.sendRedirect("/login");

        } else {
            request.getRequestDispatcher("/jsp-pages/registration.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final String date = request.getParameter("date");
        final String email = request.getParameter("email");

        Person person = personService.getPersonByCredentials("username", username);

        if (person.getUsername() == null) {

            final String hashedPassword = HashPasswordUtil.hashPassword(password);

            person = new Person(username, hashedPassword, ConvertDateUtil.convertDate(date), email);

            if (personService.addPerson(person)) {

                JavaMailUtil javaMailUtil = new JavaMailUtil("confirmRegistration",
                        personService.getPersonByCredentials("username", username));
                javaMailUtil.sendMail(person.getEmail());

            } else {
                response.sendError(500);
            }
        } else {
            response.sendError(403);
        }
    }
}
