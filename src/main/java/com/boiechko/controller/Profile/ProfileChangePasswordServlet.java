package com.boiechko.controller.Profile;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.HashingPassword.HashPasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@WebServlet("/userProfile/changePassword")
public class ProfileChangePasswordServlet extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp-pages/Profile/changePassword.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final int idPerson = Integer.parseInt(request.getParameter("idPerson"));

        final String currentPassword = request.getParameter("currentPassword");
        final String newPassword = request.getParameter("newPassword");

        final Person person = personService.getPersonById(idPerson);

        boolean isCurrentPasswordEqualNewPassword = HashPasswordUtil.checkPassword(currentPassword, person.getPassword());

        if (isCurrentPasswordEqualNewPassword) {

            person.setPassword(HashPasswordUtil.hashPassword(newPassword));

            if (!personService.updatePerson(person)) {
                response.sendError(SC_INTERNAL_SERVER_ERROR);
            }

        } else {
            response.sendError(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
