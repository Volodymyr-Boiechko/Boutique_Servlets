package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.entity.Product;
import com.boiechko.enums.PersonType;
import com.boiechko.service.implementations.ClothesServiceImpl;
import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.ClothesService;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/manClothes/*")
@MultipartConfig
public class ClothesServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();
    private final ClothesService clothesService = new ClothesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final Person person = (Person) session.getAttribute("person");

        if (person != null && person.getPersonType().equals(PersonType.ADMIN))
            request.setAttribute("show", true);

        final String page = request.getParameter("page");

        if (page.equals("1")) {

            session.removeAttribute("clothes");
            session.setAttribute("clothes", clothesService.getListOfClothes(request));

        }

        final List<Product> clothes = (List<Product>) session.getAttribute("clothes");

        request.setAttribute("amount", clothesService.getAmountOfProducts(page, clothes.size()));
        request.setAttribute("number", clothesService.getNumberOfProductsOnPage(page));
        request.setAttribute("page", page);

        request.getRequestDispatcher("/jsp-pages/clothes.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        final String typeName = request.getParameter("type");
        final String productName = request.getParameter("product");
        final String sex = request.getParameter("sex");
        final String brand = request.getParameter("brand");
        final String model = request.getParameter("model");
        final String size = request.getParameter("size");
        final String color = request.getParameter("color");
        final Part image = request.getPart("image");
        final String destination = request.getParameter("destination");
        final int price = Integer.parseInt(request.getParameter("price"));
        final String description = request.getParameter("description");

        final Product product = new Product
                (typeName, productName, sex, brand, model, size, color,
                        "dataBaseImages/" + productService.getDestinationOfImage(image, destination),
                        price, description
                );

        if (productService.saveImage(image, destination)) {

            if (!productService.addProduct(product))
                response.sendError(500);

        } else {
            response.sendError(501);
        }
    }

}
