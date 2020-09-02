package com.boiechko.service.implementations;

import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ClothesService;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClothesServiceImpl implements ClothesService {

    private final ProductService productService = new ProductServiceImpl();

    private final int NUMBER_OF_PRODUCTS_PER_PAGE = 12;

    @Override
    public List<Product> getListOfClothes(final HttpServletRequest request) {

        final String[] blocks = request.getRequestURI().split("/");

        List<Product> clothes;

        switch (blocks[2]) {
            case "clothes":
            case "shoes":
            case "accessories":
            case "sportWear": {

                final String productName = request.getParameter("productName");
                clothes = getClothes(productName, blocks[2]);
                break;
            }
            case "newestClothes": {

                clothes = productService.getLatestAddedProducts();
                break;
            }
            case "brands": {

                final String brand = request.getParameter("brand");
                clothes = productService.getProductByColumn("brand", brand);
                break;
            }
            default:
                clothes = new ArrayList<>();

        }

        return clothes;

    }

    @Override
    public int getNumberOfProductsShownOnPage(final String page, final int clothesSize) {

        int number = Integer.parseInt(page) * NUMBER_OF_PRODUCTS_PER_PAGE;

        if (number > clothesSize) {
            number = clothesSize;
        }

        return number;
    }

    @Override
    public List<Product> getFavoriteProducts(final List<Integer> idsOfProductsWhichAreFavorite) {

        return idsOfProductsWhichAreFavorite.stream()
                .map(productService::getProductById)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFavoriteProduct(final List<Integer> idsOfProductsWhichAreFavorite, final Product product) {

        return idsOfProductsWhichAreFavorite.stream()
                .anyMatch(idProduct -> idProduct == product.getIdProduct());

    }

    @Override
    public boolean isProductInShoppingBag(final List<Product> shoppingBag, final Product product) {
        
        return shoppingBag.stream()
                .anyMatch(productFromShoppingBag -> productFromShoppingBag.equals(product));

    }

    private List<Product> getClothes(final String productName, final String typeName) {

        if (productName == null) {
            return productService.getProductByColumn("typeName", getTypeName(typeName));
        } else {
            return productService.getProductByColumn("productName", productName);
        }
    }

    private String getTypeName(final String name) {

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
}