package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.JavaMailService;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/footer/sendQuestion")
public class FooterServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(FooterServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        final String firstName = request.getParameter("firstName");
        final String surname = request.getParameter("surname");
        final String lastName = request.getParameter("lastName");
        final String email = request.getParameter("email");
        final String phoneNumber = request.getParameter("phoneNumber");
        final String comment = request.getParameter("comment");

        final Person person = new Person(firstName, surname, lastName, email, phoneNumber);

        JavaMailService.sendQuestionFromUserEmail("boiechko.work@gmail.com", "questionFromUser", person, comment);

        logger.info("Відправлено лист з запитанням від користувача");

    }
}