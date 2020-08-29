package com.boiechko.controller.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/navProfile")
public class NavProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        Character letter = username.charAt(0);
        String[] path = request.getRequestURI().split("/");
        int number = -1;

        for (String s : path) {

            switch (s) {

                case "profile.jsp": number = 1; break;
                case "orders.jsp": case "orderItem.jsp" : number = 2; break;
                case "info.jsp": number = 3; break;
                case "changePassword.jsp": number = 4; break;
                case "addresses.jsp": case "editAddress.jsp": case "addAddress.jsp": number = 5; break;
                default: number = -1;

            }
        }
        request.setAttribute("number", number);
        request.setAttribute("letter", letter);

    }
}
