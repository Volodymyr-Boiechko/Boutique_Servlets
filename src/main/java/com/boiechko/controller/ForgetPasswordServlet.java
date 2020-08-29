package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.HashingPassword.HashPasswordUtil;
import com.boiechko.utils.Mail.JavaMailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/forget/*")
public class ForgetPasswordServlet extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] path = request.getRequestURI().split("/");

        if (path.length == 2 && path[1].contains("forget")) {

            request.getRequestDispatcher("/jsp-pages/ForgetPassword/forgetPassword.jsp").forward(request, response);

        } else if (path.length == 3 && path[2].contains("updatePassword")) {

            final String email = (String) request.getSession().getAttribute("email");

            if (email != null)
                request.getRequestDispatcher("/jsp-pages/ForgetPassword/updatePassword.jsp").forward(request, response);
            else
                response.sendError(404);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String email = request.getParameter("email");
        final Person person = personService.getPersonByCredentials("email", email);

        JavaMailUtil javaMailUtil = new JavaMailUtil("recoverPassword", person);
        javaMailUtil.sendMail(email);

        response.getWriter().write(javaMailUtil.getVerificationCode());

        request.getSession().setAttribute("email", email);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String password = request.getParameter("password");
        final String email = request.getParameter("email");
        final Person person = personService.getPersonByCredentials("email", email);

        final boolean equals = HashPasswordUtil.checkPassword(password, person.getPassword());

        if (!equals) {

            person.setPassword(HashPasswordUtil.hashPassword(password));

            if (personService.updatePerson(person)) {

                request.getSession().removeAttribute("email");

            } else {
                response.sendError(500);
            }
        } else {
            response.sendError(403);
        }
    }

}