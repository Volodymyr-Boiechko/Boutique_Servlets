package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.JavaMailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forget")
public class ForgetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("jsp-pages/forgetPassword.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");

        PersonService personService = new PersonServiceImpl();

        Person person = personService.getPersonByCredentials("email", email);

        if (person.getUsername() != null) {

            JavaMailUtil javaMailUtil = new JavaMailUtil();

            javaMailUtil.sendMail(email);

            String code = javaMailUtil.getCode();


        } else {

            response.sendError(403);

        }
    }
}