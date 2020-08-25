package com.boiechko.controller;

import com.boiechko.entity.Product;
import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shoppingBag")
public class ShoppingBagServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("shoppingBag") == null)
            session.setAttribute("shoppingBag", new ArrayList<Product>());

        List<Product> products = (List<Product>) session.getAttribute("shoppingBag");
        List<Integer> prices = new ArrayList<>();

        for (Product product: products)
            prices.add(product.getPrice());

        request.setAttribute("prices", prices);

        request.getRequestDispatcher("/jsp-pages/shoppingBag.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final int id = Integer.parseInt(request.getParameter("idProduct"));
        String username = (String) session.getAttribute("username");

        Product product = productService.getById(id);

        if (username != null) {

            if (product.getTypeName() != null) {

                try {

                    List<Product> shoppingBag = (List<Product>) session.getAttribute("shoppingBag");

                    boolean isInBag = false;

                    for (Product el : shoppingBag)
                        if (el.equals(product)) {
                            isInBag = true;
                            break;
                        }

                    if (!isInBag) {

                        shoppingBag.add(product);
                        session.setAttribute("shoppingBag", shoppingBag);

                        List<Integer> favoriteId = (List<Integer>) session.getAttribute("favoriteId");
                        favoriteId.remove(Integer.valueOf(product.getIdProduct()));
                        session.setAttribute("favoriteId", favoriteId);

                    } else {
                        response.sendError(403);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(500);
                }
            } else {
                response.sendError(500);
            }
        } else {
            response.sendError(401);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final int id = Integer.parseInt(request.getParameter("idProduct"));
        Product product = productService.getById(id);

        if (product.getTypeName() != null) {

            try {

                List<Integer> shoppingBag = (List<Integer>) session.getAttribute("shoppingBag");

                shoppingBag.remove(product);

                session.setAttribute("shoppingBag", shoppingBag);

            } catch (Exception e) {
                response.sendError(500);
                e.printStackTrace();
            }

        } else {
            response.sendError(500);
        }

    }
}
