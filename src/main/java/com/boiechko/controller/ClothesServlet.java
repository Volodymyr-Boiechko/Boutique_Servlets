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

@WebServlet("/manClothes/*")
public class ClothesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductService productService = new ProductServiceImpl();

        request.removeAttribute("clothes");

        String[] blocks = request.getRequestURI().split("/");

        switch (blocks[2]) {
            case "clothes":
            case "shoes":
            case "accessories":
            case "sportWear": {

                String productName = request.getParameter("productName");
                List<Product> clothes;

                if (productName == null) {

                    clothes = productService.getAllByCredentials("typeName", getTypeName(blocks[2]));
                } else  {
                    clothes = productService.getAllByCredentials("productName", productName);
                }


                request.setAttribute("clothes", clothes);

                break;
            }
            case "newestClothes": {

                List<Product> clothes = productService.getNewest().stream().limit(15).collect(Collectors.toList());

                request.setAttribute("clothes", clothes);

                break;
            }
            case "brands": {

                String brand = request.getParameter("brand");

                List<Product> clothes = productService.getAllByCredentials("brand", brand);

                request.setAttribute("clothes", clothes);

                break;
            }
            default: {

                request.setAttribute("clothes", new ArrayList<Product>());

            }
        }

        request.getRequestDispatcher("/jsp-pages/clothes/clothes.jsp").forward(request,response);

    }

    private String getTypeName(String name) {

        switch (name) {
            case "clothes": return "Одяг";
            case "shoes": return "Взуття";
            case "accessories": return "Аксесуари";
            case "sportWear": return "Спортивний одяг";
            default: return null;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
