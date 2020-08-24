package com.boiechko.controller;

import com.boiechko.entity.Product;
import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/manClothes/*")
@MultipartConfig
public class ClothesServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();
    private final String appPath = "C:\\Users\\volod\\IdeaProjects\\Boutique_Servlets\\web\\dataBaseImages\\";

    private final int amountProductsInPage = 12;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String page = request.getParameter("page");

        if (page.equals("1")) {

            session.removeAttribute("clothes");
            session.removeAttribute("count");

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
                    } else {
                        clothes = productService.getAllByCredentials("productName", productName);
                    }

                    session.setAttribute("clothes", clothes);
                    break;
                }
                case "newestClothes": {

                    List<Product> clothes = productService.getNewest();

                    session.setAttribute("clothes", clothes);

                    break;
                }
                case "brands": {

                    String brand = request.getParameter("brand");

                    List<Product> clothes = productService.getAllByCredentials("brand", brand);

                    session.setAttribute("clothes", clothes);

                    break;
                }
                default: {

                    session.setAttribute("clothes", new ArrayList<Product>());

                }
            }

        }

        List<Product> products = (List<Product>) session.getAttribute("clothes");

        int count = Integer.parseInt(page) * amountProductsInPage - 1;

        if (count >= products.size())
            count = products.size() - 1;

        session.setAttribute("count", Integer.toString(count));
        session.setAttribute("page", page);

        request.getRequestDispatcher("/jsp-pages/clothes.jsp").forward(request, response);

    }

    private String getTypeName(String name) {

        switch (name) {
            case "clothes":
                return "Одяг";
            case "shoes":
                return "Взуття";
            case "accessories":
                return "Аксесуари";
            case "sportWear":
                return "Спортивний одяг";
            default:
                return null;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

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

            Product product = new Product
                    (typeName, productName, sex, brand, model, size, color,
                            "dataBaseImages/" + getDestination(image, destination), price, description
                    );

            if (saveImage(image, destination)) {

                if (!productService.add(product)) {

                    response.sendError(500);

                }

            } else {

                response.sendError(501);

            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }

    }

    private String getDestination(Part image, String destination) {

        return destination + "/" + image.getSubmittedFileName();

    }

    private boolean saveImage(Part image, String destination) throws IOException {

        String imagePath = appPath + destination.replace("/", "\\");

        File fileDir = new File(imagePath);
        if (!fileDir.exists())
            fileDir.mkdirs();

        String imageName = image.getSubmittedFileName();

        if (validateImage(imageName)) {

            image.write(imagePath + File.separator + imageName);

        } else {
            return false;
        }

        return true;

    }

    private boolean validateImage(String imageName) {

        String fileExt = imageName.substring(imageName.length() - 3);

        return "jpg".equals(fileExt) || "png".equals(fileExt) || "gif".equals(fileExt);
    }

}
