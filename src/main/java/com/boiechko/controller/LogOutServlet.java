package com.boiechko.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();

        final Enumeration<String> sessionAttributeNames = session.getAttributeNames();

        if (sessionAttributeNames.hasMoreElements()) {
            session.invalidate();
            request.getRequestDispatcher("/jsp-pages/components/logOut.jsp").forward(request, response);
        } else {
            response.sendError(SC_NOT_FOUND);
        }

    }
}