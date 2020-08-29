package com.boiechko.controller;

import com.boiechko.entity.Product;
import com.boiechko.service.implementations.AddressServiceImpl;
import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.AddressService;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/shoppingBag")
public class ShoppingBagServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();
    private final AddressService addressService = new AddressServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Product> products = (List<Product>) session.getAttribute("shoppingBag");
        if (products != null) {

            List<Integer> prices = new ArrayList<>();

            for (Product product: products)
                prices.add(product.getPrice());

            final int idUser = (int) session.getAttribute("userId");

            request.setAttribute("prices", prices);
            request.setAttribute("addresses", addressService.getAddressesOfUser(idUser));
            request.setAttribute("count", new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)));
        }

        request.getRequestDispatcher("/jsp-pages/shoppingBag.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        final int id = Integer.parseInt(request.getParameter("idProduct"));
        String username = (String) session.getAttribute("username");

        Product product = productService.getProductById(id);

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
                        if (favoriteId.contains(product.getIdProduct())) {
                            favoriteId.remove(Integer.valueOf(product.getIdProduct()));
                            session.setAttribute("favoriteId", favoriteId);
                        }

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
        Product product = productService.getProductById(id);

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
