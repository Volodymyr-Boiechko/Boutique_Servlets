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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/headerServlet")
public class HeaderServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("newestProducts", productService.getNewest().stream().limit(3).collect(Collectors.toList()));

        request.setAttribute("clothesTypes", productService.getUniqueFields("productName", "typeName", "Одяг"));

        final List<Product> shoes = productService.getUniqueFields("productName", "typeName", "Взуття");
        request.setAttribute("shoes", shoes);
        request.setAttribute("shoesBrands", shoes.stream().limit(4).collect(Collectors.toList()));
        request.setAttribute("shoesImages", productService.getAllByCredentials("typeName", "Взуття").stream().limit(2).collect(Collectors.toList()));

        final List<Product> accessories = productService.getUniqueFields("productName", "typeName", "Аксесуари");
        request.setAttribute("accessories", accessories);
        request.setAttribute("accessoriesBrands", accessories.stream().limit(4).collect(Collectors.toList()));
        request.setAttribute("accessoriesImages", productService.getAllByCredentials("typeName", "Аксесуари").stream().limit(2).collect(Collectors.toList()));

        final List<Product> sportWear = productService.getUniqueFields("productName", "typeName", "Спортивний одяг");
        request.setAttribute("sportWear", sportWear);
        request.setAttribute("sportWearImages", productService.getAllByCredentials("typeName", "Спортивний одяг").stream().limit(3).collect(Collectors.toList()));

        final List<Product> allBrands = productService.groupBy("brand");
        List<Product> brands = new ArrayList<>();

        for (Product product : allBrands) {

            List<Product> count = productService.getAllByCredentials("brand", product.getBrand());

            if (count.size() >= 10)
                brands.add(product);

        }

        request.setAttribute("brands", brands);
        request.setAttribute("brandsImages", brands.stream().limit(2).collect(Collectors.toList()));

    }
}