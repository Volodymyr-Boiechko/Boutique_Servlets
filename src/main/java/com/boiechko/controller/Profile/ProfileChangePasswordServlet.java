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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userProfile/changePassword")
public class ProfileChangePasswordServlet extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        PersonService personService = new PersonServiceImpl();
        Person person = personService.getPersonById((Integer) session.getAttribute("userId"));
        request.setAttribute("person", person);

        request.getRequestDispatcher("/jsp-pages/Profile/changePassword.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int id = Integer.parseInt(request.getParameter("id"));

        final String currentPassword = request.getParameter("currentPassword");
        final String newPassword = request.getParameter("newPassword");

        final Person person = personService.getPersonById(id);

        if (person.getUsername() != null) {

            boolean isEqual = HashPasswordUtil.checkPassword(currentPassword, person.getPassword());

            if (isEqual) {

                person.setPassword(HashPasswordUtil.hashPassword(newPassword));

                if (personService.updatePerson(person)) {
                    request.getSession().removeAttribute("person");
                } else {
                    response.sendError(500);
                }
            } else {
                response.sendError(401);
            }
        } else {
            response.sendError(500);
        }
    }
}
