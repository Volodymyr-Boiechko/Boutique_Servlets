package com.boiechko.controller;

import com.boiechko.service.implementations.ClothesServiceImpl;
import com.boiechko.service.interfaces.ClothesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/headerServlet")
public class HeaderServlet extends HttpServlet {

    private final ClothesService clothesService = new ClothesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("newestProducts", clothesService.getNewest());

        request.setAttribute("clothesTypes",
                clothesService.getUniqueFields("productName", "typeName", "Одяг"));

        request.setAttribute("shoes",
                clothesService.getUniqueFields("productName", "typeName", "Взуття"));

        request.setAttribute("accessories",
                clothesService.getUniqueFields("productName", "typeName", "Аксесуари"));

        request.setAttribute("sportWear",
                clothesService.getUniqueFields("productName", "typeName", "Спортивний одяг"));

        request.setAttribute("brands", clothesService.getPopularBrands());

    }
}