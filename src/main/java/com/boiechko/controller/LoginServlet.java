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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("jsp-pages/login.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        final String username = request.getParameter("username");
        final String hashedPassword = request.getParameter("password");

        PersonService personService = new PersonServiceImpl();
        Person person = personService.getPersonByCredentials("username", username);

        if (person.getUsername() != null) {

            boolean isEqual = HashPasswordUtil.checkPassword(hashedPassword, person.getPassword());

            if (isEqual) {

                HttpSession session = request.getSession();

                session.setAttribute("username", username);

            } else {

                response.sendError(401);

            }
        } else {

            response.sendError(403);
        }
    }
}