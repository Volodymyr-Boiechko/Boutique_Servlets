package com.boiechko.service.implementations;

import com.boiechko.service.interfaces.PersonProfileService;

import javax.servlet.http.HttpServletRequest;

public class PersonProfileServiceImpl implements PersonProfileService {

    @Override
    public int getNumberOfProfileNavigation(final HttpServletRequest request) {

        final String[] path = request.getRequestURI().split("/");
        int number = -1;

        switch (path[path.length - 1]) {

            case "profile.jsp": number = 1; break;
            case "orders.jsp": case "orderItem.jsp": number = 2; break;
            case "info.jsp": number = 3; break;
            case "changePassword.jsp": number = 4; break;
            case "addresses.jsp": case "editAddress.jsp": case "addAddress.jsp": number = 5; break;
        }
        return number;
    }
}
