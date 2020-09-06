package com.boiechko.service.implementations;

import com.boiechko.service.interfaces.ProfileOfPersonService;

import javax.servlet.http.HttpServletRequest;

import static com.boiechko.enums.NavigationBar.*;

public class ProfileOfPersonServiceImpl implements ProfileOfPersonService {

    @Override
    public int getNumberOfSectionInNavigationBar(final HttpServletRequest request) {

        final String[] urlPages = request.getRequestURI().split("/");

        int numberOfSection;

        //todo зробити enum
        switch (urlPages[urlPages.length - 1]) {

            case "profile.jsp":
                numberOfSection = PROFILE.getNumberOfSection();
                break;
            case "orders.jsp": case "orderItem.jsp":
                numberOfSection = ORDERS.getNumberOfSection();
                break;
            case "info.jsp":
                numberOfSection = PERSON_INFO.getNumberOfSection();
                break;
            case "changePassword.jsp":
                numberOfSection = PERSON_CHANGE_PASSWORD.getNumberOfSection();
                break;
            case "addresses.jsp": case "editAddress.jsp": case "addAddress.jsp":
                numberOfSection = ADDRESSES_OF_PERSON.getNumberOfSection();
                break;
            default:
                numberOfSection = UNDEFINED.getNumberOfSection();

        }
        return numberOfSection;
    }
}
