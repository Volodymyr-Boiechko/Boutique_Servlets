package com.boiechko.controller.Profile;

import com.boiechko.service.implementations.PersonProfileServiceImpl;
import com.boiechko.service.interfaces.PersonProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/navProfile")
public class NavProfileServlet extends HttpServlet {

    private final PersonProfileService personProfileService = new PersonProfileServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final String username = (String) session.getAttribute("username");
        final Character letter = username.charAt(0);

        request.setAttribute("number", personProfileService.getNumberOfProfileNavigation(request));
        request.setAttribute("letter", letter);

    }
}
