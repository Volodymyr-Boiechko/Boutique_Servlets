package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.HashingPassword.HashPasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp-pages/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final HttpSession session = request.getSession();

        final String username = request.getParameter("username");
        final String enteredPassword = request.getParameter("password");

        final Person person = personService.getPersonByColumn("username", username);

        if (person.getUsername() != null) {

            if (person.getActivationCode() == null) {

                boolean isEnteredPasswordEqualPersonPassword = HashPasswordUtil.checkPassword(enteredPassword, person.getPassword());

                if (isEnteredPasswordEqualPersonPassword) {

                    session.setAttribute("username", username);
                    session.setAttribute("person", personService.getPersonByColumn("username", username));

                } else {
                    response.sendError(SC_UNAUTHORIZED);
                }
            } else {
                response.sendError(SC_BAD_REQUEST);
            }
        } else {
            response.sendError(SC_FORBIDDEN);
        }
    }
}