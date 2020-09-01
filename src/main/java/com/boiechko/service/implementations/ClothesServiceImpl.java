package com.boiechko.service.implementations;

import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ClothesService;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ClothesServiceImpl implements ClothesService {

    private final ProductService productService = new ProductServiceImpl();

    private final int amountProductsOnPage = 12;

    @Override
    public List<Product> getListOfClothes(final HttpServletRequest request) {

        final String[] blocks = request.getRequestURI().split("/");

        List<Product> clothes;

        switch (blocks[2]) {
            case "clothes":
            case "shoes":
            case "accessories":
            case "sportWear": {


                // todo винести в окремий метод
                final String productName = request.getParameter("productName");

                if (productName == null)
                    clothes = productService.getProductByColumn("typeName", getTypeName(blocks[2]));
                else
                    clothes = productService.getProductByColumn("productName", productName);

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

        int number = Integer.parseInt(page) * amountProductsOnPage;

        if (number > clothesSize)
            number = clothesSize;

        return number;
    }

    @Override
    public List<Product> getFavoriteProducts(final List<Integer> idsOfProductsWhichAreFavorite) {

        //todo на стріми
        List<Product> favorite = new ArrayList<>();
        for (Integer id : idsOfProductsWhichAreFavorite)
            favorite.add(productService.getProductById(id));

        return favorite;

    }

    @Override
    public boolean isFavoriteProduct(final List<Integer> idsOfProductsWhichAreFavorite, final Product product) {

        boolean isInFavorite = false;

        for (Integer elementId : idsOfProductsWhichAreFavorite)
            if (elementId == product.getIdProduct()) {
                isInFavorite = true;
                break;
            }

        return isInFavorite;

    }

    @Override
    public boolean isProductInShoppingBag(final List<Product> shoppingBag, final Product product) {

        boolean isInBag = false;

        for (Product el : shoppingBag)
            if (el.equals(product)) {
                isInBag = true;
                break;
            }

        return isInBag;
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