package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.utils.JavaMailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/footerServlet")
public class FooterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String firstName = request.getParameter("firstName");
        final String surname = request.getParameter("surname");
        final String lastName = request.getParameter("lastName");
        final String email = request.getParameter("email");
        final String phoneNumber = request.getParameter("phoneNumber");
        final String comment = request.getParameter("comment");

        Person person = new Person(firstName, surname, lastName, email, phoneNumber);

        JavaMailUtil javaMailUtil = new JavaMailUtil("questionFromUser");
        javaMailUtil.setPerson(person);
        javaMailUtil.setComment(comment);
        javaMailUtil.sendMail("boiechko.work@gmail.com");

    }
}