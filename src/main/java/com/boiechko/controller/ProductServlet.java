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

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int id = Integer.parseInt(request.getParameter("idProduct"));
        final Product product = productService.getProductById(id);

        final List<Product> products = productService.getAllByCredentials("productName",
                product.getProductName()).stream().filter(i -> !i.equals(product)).limit(4).collect(Collectors.toList());

        request.setAttribute("product", product);
        request.setAttribute("mayLike", products);
        request.setAttribute("path", productService.getPathToProduct(request, product));

        request.getRequestDispatcher("/jsp-pages/product.jsp").forward(request, response);

    }
}
