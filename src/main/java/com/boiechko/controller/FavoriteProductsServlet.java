package com.boiechko.controller;

import com.boiechko.entity.Product;
import com.boiechko.service.implementations.ClothesServiceImpl;
import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.ClothesService;
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

    private final ProductService productService = new ProductServiceImpl();
    private final ClothesService clothesService = new ClothesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final List<Integer> favoriteId = (List<Integer>) session.getAttribute("favoriteId");

        if (favoriteId != null)
            request.setAttribute("favorite", clothesService.getFavoriteClothes(favoriteId));

        request.getRequestDispatcher("/jsp-pages/favoriteProducts.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        final String username = (String) session.getAttribute("username");

        if (username != null) {

            final int idProduct = Integer.parseInt(request.getParameter("idProduct"));
            final Product product = productService.getProductById(idProduct);

            List<Integer> favorite = (List<Integer>) session.getAttribute("favoriteId");
            List<Product> shoppingBag = (List<Product>) session.getAttribute("shoppingBag");

            if (!clothesService.isInFavorite(favorite, product)) {

                favorite.add(product.getIdProduct());
                session.setAttribute("favoriteId", favorite);
                response.getWriter().write("add");

                shoppingBag.remove(product);
                session.setAttribute("shoppingBag", shoppingBag);

            } else {

                doDelete(request, response);
                response.getWriter().write("remove");
            }
        } else {
            response.sendError(401);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        final Product product = productService.getProductById(idProduct);

        final List<Integer> favorite = (List<Integer>) session.getAttribute("favoriteId");

        favorite.remove(Integer.valueOf(product.getIdProduct()));
        session.setAttribute("favoriteId", favorite);


    }
}
