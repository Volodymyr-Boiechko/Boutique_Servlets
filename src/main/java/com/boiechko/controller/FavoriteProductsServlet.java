package com.boiechko.controller;

import com.boiechko.entity.Product;
import com.boiechko.service.implementations.ClothesServiceImpl;
import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.ClothesService;
import com.boiechko.service.interfaces.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@WebServlet("/favorite")
public class FavoriteProductsServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(FavoriteProductsServlet.class);

    private final ProductService productService = new ProductServiceImpl();
    private final ClothesService clothesService = new ClothesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();

        final List<Integer> idsOfProductsThatAreFavorite = (List<Integer>) session.getAttribute("idsOfProductsThatAreFavorite");

        if (idsOfProductsThatAreFavorite != null) {
            request.setAttribute("favoriteProducts",
                    clothesService.getFavoriteProducts(idsOfProductsThatAreFavorite));
        }

        request.getRequestDispatcher("/jsp-pages/favoriteProducts.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final HttpSession session = request.getSession();
        final String username = (String) session.getAttribute("username");

        if (username != null) {

            final int idProduct = Integer.parseInt(request.getParameter("idProduct"));
            final Product product = productService.getProductById(idProduct);

            List<Integer> idsOfProductsThatAreFavorite = (List<Integer>) session.getAttribute("idsOfProductsThatAreFavorite");
            List<Product> shoppingBag = (List<Product>) session.getAttribute("shoppingBag");

            if (!clothesService.isFavoriteProduct(idsOfProductsThatAreFavorite, product)) {

                idsOfProductsThatAreFavorite.add(product.getIdProduct());
                session.setAttribute("idsOfProductsThatAreFavorite", idsOfProductsThatAreFavorite);
                response.getWriter().write("add");

                shoppingBag.remove(product);
                session.setAttribute("shoppingBag", shoppingBag);

                logger.info(idProduct + " збережено в улюблених");

            } else {

                doDelete(request, response);
                response.getWriter().write("remove");

                logger.info(idProduct + " видалено з улюблених");
            }
        } else {
            response.sendError(SC_UNAUTHORIZED);
            logger.warn("користувач хотів добавити до улюблених продукт не увійшовши в систему");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        final HttpSession session = request.getSession();

        final int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        final Product product = productService.getProductById(idProduct);

        final List<Integer> idsOfProductsThatAreFavorite = (List<Integer>) session.getAttribute("idsOfProductsThatAreFavorite");

        idsOfProductsThatAreFavorite.remove(Integer.valueOf(product.getIdProduct()));
        session.setAttribute("idsOfProductsThatAreFavorite", idsOfProductsThatAreFavorite);

        logger.info(idProduct + " видалено з улюблених");

    }
}
