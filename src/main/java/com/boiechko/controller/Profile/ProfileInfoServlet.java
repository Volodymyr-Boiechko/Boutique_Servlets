package com.boiechko.controller.Profile;

import com.boiechko.entity.Person;
import com.boiechko.service.implementations.PersonServiceImpl;
import com.boiechko.service.interfaces.PersonService;
import com.boiechko.utils.ConvertDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@WebServlet("/userProfile/userInfo")
public class ProfileInfoServlet extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp-pages/Profile/info.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final int idPerson = Integer.parseInt(request.getParameter("idPerson"));

        final String firstName = request.getParameter("firstName");
        final String surname = request.getParameter("surname");
        final String lastName = request.getParameter("lastName");
        final String date = request.getParameter("birthDate");
        final String email = request.getParameter("email");
        final String phoneNumber = request.getParameter("phoneNumber");

        final Person person = personService.getPersonById(idPerson);

        person.setFirstName(firstName);
        person.setSurname(surname);
        person.setLastName(lastName);
        person.setBirthDate(ConvertDateUtil.convertDate(date));
        person.setEmail(email);
        person.setPhoneNumber(phoneNumber);

        if (!personService.updatePerson(person)) {
            response.sendError(SC_INTERNAL_SERVER_ERROR);
        }

        request.getSession().setAttribute("person", person);
    }
}
