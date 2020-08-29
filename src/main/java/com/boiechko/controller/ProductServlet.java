package com.boiechko.controller;

import com.boiechko.entity.Product;
import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/manClothes/productItem")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductService productService = new ProductServiceImpl();

        String[] urlParts = request.getRequestURI().split("/");

        String sex = urlParts[1].equals("manClothes") ? "Чоловіче" : "Жіноче";

        final int id = Integer.parseInt(request.getParameter("idProduct"));

        Product product = productService.getProductById(id);

        List<String> parts = new ArrayList<>(Arrays.asList(sex, getTypeName(product.getTypeName())));

        List<Product> products = productService.getAllByCredentials("productName",
                product.getProductName()).stream().filter(i -> !i.equals(product)).limit(4).collect(Collectors.toList());

        request.setAttribute("product", product);
        request.setAttribute("mayLike", products);
        request.setAttribute("path", parts);

        request.getRequestDispatcher("/jsp-pages/product.jsp").forward(request, response);

    }

    private String getTypeName(String name) {

        switch (name) {
            case "Одяг":
                return "clothes";
            case "Взуття":
                return "shoes";
            case "Аксесуари":
                return "accessories";
            case "Спортивний одяг":
                return "sportWear";
            default:
                return null;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
