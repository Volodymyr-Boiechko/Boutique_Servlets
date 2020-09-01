package com.boiechko.controller;

import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@WebServlet("/headerServlet")
public class HeaderServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("newestProducts",
                productService.getLatestAddedProducts().stream().limit(30).collect(Collectors.toList()));

        request.setAttribute("clothesTypes",
                productService.groupByColumnWithCondition("typeName", "Одяг", "productName"));

        request.setAttribute("shoes",
                productService.groupByColumnWithCondition("typeName", "Взуття", "productName"));

        request.setAttribute("accessories",
                productService.groupByColumnWithCondition("typeName", "Аксесуари", "productName"));

        request.setAttribute("sportWear",
                productService.groupByColumnWithCondition("typeName", "Спортивний одяг", "productName"));

        request.setAttribute("brands", productService.getPopularBrands());

    }
}