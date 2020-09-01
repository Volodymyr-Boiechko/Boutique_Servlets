package com.boiechko.controller.Profile;

import com.boiechko.service.implementations.ProfileOfPersonServiceImpl;
import com.boiechko.service.interfaces.ProfileOfPersonService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/navProfile")
public class NavProfileServlet extends HttpServlet {

    private final ProfileOfPersonService profileOfPersonService = new ProfileOfPersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        final HttpSession session = request.getSession();

        final String username = (String) session.getAttribute("username");
        final Character firstLetterOfUsername = username.charAt(0);

        request.setAttribute("numberOfSectionInNavigationBar", profileOfPersonService.getNumberOfSectionInNavigationBar(request));
        request.setAttribute("firstLetterOfUsername", firstLetterOfUsername);

    }
}
