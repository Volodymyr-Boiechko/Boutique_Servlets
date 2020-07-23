package com.boiechko.controller.ForgetPassword;

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

@WebServlet("/forget/updatePassword")
public class UpdatePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp-pages/ForgetPassword/updatePassword.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String password = request.getParameter("password");
        final String email = request.getParameter("email");

        PersonService personService = new PersonServiceImpl();

        Person person = personService.getPersonByCredentials("email",email);

        if (person.getUsername() != null) {

            boolean equals = HashPasswordUtil.checkPassword(password, person.getPassword());

            if (!equals) {

                person.setPassword(HashPasswordUtil.hashPassword(password));

                if (personService.update(person)) {

                    HttpSession session = request.getSession();

                    session.invalidate();

                } else {
                    response.sendError(503);
                }
            } else {
                response.sendError(403);
            }
        } else {
            response.sendError(500);
        }
    }
}