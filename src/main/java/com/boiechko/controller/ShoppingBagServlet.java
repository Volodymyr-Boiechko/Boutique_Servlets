package com.boiechko.controller;

import com.boiechko.entity.Person;
import com.boiechko.entity.Product;
import com.boiechko.service.implementations.AddressServiceImpl;
import com.boiechko.service.implementations.ClothesServiceImpl;
import com.boiechko.service.implementations.ProductServiceImpl;
import com.boiechko.service.interfaces.AddressService;
import com.boiechko.service.interfaces.ClothesService;
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
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@WebServlet("/shoppingBag")
public class ShoppingBagServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();
    private final AddressService addressService = new AddressServiceImpl();
    private final ClothesService clothesService = new ClothesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final List<Product> products = (List<Product>) request.getSession().getAttribute("shoppingBag");

        if (products != null && products.size() != 0) {

            final Person person = (Person) request.getSession().getAttribute("person");

            request.setAttribute("pricesOfProducts",
                    products.stream().map(Product::getPrice).sequential().collect(Collectors.toList()));

            request.setAttribute("addressesOfPerson", addressService.getAllAddressesOfPerson(person.getIdPerson()));
            request.setAttribute("maxQuantity", new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)));
        }

        request.getRequestDispatcher("/jsp-pages/shoppingBag.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final HttpSession session = request.getSession();
        final String username = (String) session.getAttribute("username");

        final int idProduct = Integer.parseInt(request.getParameter("idProduct"));

        if (username != null) {

            final Product product = productService.getProductById(idProduct);
            List<Product> shoppingBag = (List<Product>) session.getAttribute("shoppingBag");
            List<Integer> idsOfProductsWhichAreFavorite = (List<Integer>) session.getAttribute("idsOfProductsThatAreFavorite");

            if (!clothesService.isProductInShoppingBag(shoppingBag, product)) {

                shoppingBag.add(product);
                session.setAttribute("shoppingBag", shoppingBag);

                idsOfProductsWhichAreFavorite.remove(Integer.valueOf(product.getIdProduct()));
                session.setAttribute("idsOfProductsThatAreFavorite", idsOfProductsWhichAreFavorite);

            } else {
                response.sendError(SC_FORBIDDEN);
            }

        } else {
            response.sendError(SC_UNAUTHORIZED);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        final HttpSession session = request.getSession();

        final int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        final Product product = productService.getProductById(idProduct);

        List<Product> shoppingBag = (List<Product>) session.getAttribute("shoppingBag");
        shoppingBag.remove(product);
        session.setAttribute("shoppingBag", shoppingBag);

    }
}
