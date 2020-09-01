package com.boiechko.service.implementations;

import com.boiechko.service.interfaces.ProfileOfPersonService;

import javax.servlet.http.HttpServletRequest;

public class ProfileOfPersonServiceImpl implements ProfileOfPersonService {

    @Override
    public int getNumberOfSectionInNavigationBar(final HttpServletRequest request) {

        final String[] path = request.getRequestURI().split("/");

        int numberOfSection = -1;

        switch (path[path.length - 1]) {

            case "profile.jsp": numberOfSection = 1; break;
            case "orders.jsp": case "orderItem.jsp": numberOfSection = 2; break;
            case "info.jsp": numberOfSection = 3; break;
            case "changePassword.jsp": numberOfSection = 4; break;
            case "addresses.jsp": case "editAddress.jsp": case "addAddress.jsp": numberOfSection = 5; break;

        }
        return numberOfSection;
    }
}
