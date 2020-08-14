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
import java.util.List;

@WebServlet("/favorite")
public class FavoriteProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("username") == null)
            response.sendError(404);
        else {

            request.setAttribute("favorite", session.getAttribute("favorite"));

            request.getRequestDispatcher("/jsp-pages/favoriteProducts.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {

            ProductService productService = new ProductServiceImpl();

            final int idProduct = Integer.parseInt(request.getParameter("idProduct"));
            Product product = productService.getById(idProduct);

            if (product.getTypeName() != null) {

                try {

                    List<Integer> favorite = (List<Integer>) session.getAttribute("favoriteId");

                    boolean isInFavorite = false;

                    for (Integer elementId : favorite)
                        if (elementId == product.getIdProduct()) {
                            isInFavorite = true;
                            break;
                        }

                    if (!isInFavorite) {
                        favorite.add(product.getIdProduct());
                        session.setAttribute("favoriteId", favorite);
                        response.getWriter().write("add");
                    } else {

                        doDelete(request, response);
                        response.getWriter().write("remove");

                    }


                } catch (Exception e) {
                    response.sendError(500);
                    e.printStackTrace();
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
        ProductService productService = new ProductServiceImpl();

        final int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        Product product = productService.getById(idProduct);

        if (product.getTypeName() != null) {

            try {

                List<Integer> favorite = (List<Integer>) session.getAttribute("favoriteId");

                favorite.remove(Integer.valueOf(product.getIdProduct()));
                session.setAttribute("favoriteId", favorite);

            } catch (Exception e) {
                response.sendError(500);
                e.printStackTrace();
            }

        } else {
            response.sendError(500);
        }
    }
}
