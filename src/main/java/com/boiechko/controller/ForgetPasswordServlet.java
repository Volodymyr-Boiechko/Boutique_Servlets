package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.JavaMailService;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.HashingPassword.HashPasswordUtil;
import com.boiechko.utils.Mail.JavaMailUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

@WebServlet("/forget/*")
public class ForgetPasswordServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(ForgetPasswordServlet.class);

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String[] path = request.getRequestURI().split("/");

        if (path.length == 3 && path[2].contains("updatePassword")) {

            final String email = (String) request.getSession().getAttribute("email");

            if (email != null) {
                request.getRequestDispatcher("/jsp-pages/ForgetPassword/updatePassword.jsp").forward(request, response);
            } else {
                response.sendError(SC_NOT_FOUND);
            }

        } else if (path.length == 2 && path[1].contains("forget")) {

            request.getRequestDispatcher("/jsp-pages/ForgetPassword/forgetPassword.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String email = request.getParameter("email");
        final Person person = personService.getPersonByColumn("email", email);

        if (person != null) {

            String verificationCode = JavaMailService.sendRecoveryPasswordEmail(email, "recoverPassword", person);

            response.getWriter().write(verificationCode);

            request.getSession().setAttribute("email", email);

            logger.warn(person.getUsername() + " відправлено лист з відновленням паролю");

        } else {
            response.sendError(SC_FORBIDDEN);
            logger.warn("Користувача не знайдено");
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String password = request.getParameter("password");
        final String email = request.getParameter("email");
        final Person person = personService.getPersonByColumn("email", email);

        final boolean isOldPasswordEqualNewPassword = HashPasswordUtil.checkPassword(password, person.getPassword());

        if (!isOldPasswordEqualNewPassword) {

            person.setPassword(HashPasswordUtil.hashPassword(password));

            if (personService.updatePerson(person)) {

                request.getSession().removeAttribute("email");

                logger.info(person.getUsername() + " оновив пароль");

            } else {
                response.sendError(SC_INTERNAL_SERVER_ERROR);
                logger.error("Не вдалось оновити пароль користувача");
            }
        } else {
            response.sendError(SC_FORBIDDEN);
            logger.warn(person.getUsername() + " старий і новий пароль повторюються");
        }
    }

}
