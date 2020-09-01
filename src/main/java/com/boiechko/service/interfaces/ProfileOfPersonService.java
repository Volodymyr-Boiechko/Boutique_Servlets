package com.boiechko.service.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface ProfileOfPersonService {

    int getNumberOfSectionInNavigationBar(final HttpServletRequest request);

}
