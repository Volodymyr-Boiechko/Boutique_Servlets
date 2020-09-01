package com.boiechko.controller.filter;

import com.boiechko.entity.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/*")
public class MainFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        final HttpSession session = httpRequest.getSession();

        if (session.getAttribute("idsOfProductsThatAreFavorite") == null)
            session.setAttribute("idsOfProductsThatAreFavorite", new ArrayList<Integer>());
        if (session.getAttribute("shoppingBag") == null)
            session.setAttribute("shoppingBag", new ArrayList<Product>());

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
    }
}
