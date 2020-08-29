package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/logout/*")
public class LogOutServlet extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String[] pathParts = request.getRequestURI().split("/");

        if (pathParts.length != 2 ){

            final String username = pathParts[2];

            final Person person = personService.getPersonByCredentials("username", username);

            if (person.getUsername() != null) {

                Enumeration<String> names = session.getAttributeNames();

                if (names.hasMoreElements()) {
                    session.invalidate();
                    request.getRequestDispatcher("/jsp-pages/components/logOut.jsp").forward(request,response);
                } else {
                    response.sendError(404);
                }

            } else {
                response.sendError(404);
            }

        } else {
            response.sendError(404);
        }
    }
}