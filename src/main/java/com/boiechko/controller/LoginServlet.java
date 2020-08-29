package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.entity.Product;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp-pages/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final String username = request.getParameter("username");
        final String hashedPassword = request.getParameter("password");

        final Person person = personService.getPersonByCredentials("username", username);

        if (person.getUsername() != null) {

            if (person.getActivationCode() == null) {

                boolean isEqual = HashPasswordUtil.checkPassword(hashedPassword, person.getPassword());

                if (isEqual) {

                    session.setAttribute("username", username);
                    session.setAttribute("person", personService.getPersonByCredentials("username", username));

                    session.setAttribute("favoriteId", new ArrayList<Integer>());
                    session.setAttribute("shoppingBag", new ArrayList<Product>());

                } else {
                    response.sendError(401);
                }
            } else {
                response.sendError(402);
            }
        } else {
            response.sendError(403);
        }
    }
}