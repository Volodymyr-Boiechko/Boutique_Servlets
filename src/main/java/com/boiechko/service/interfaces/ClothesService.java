package com.boiechko.service.interfaces;

import com.boiechko.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClothesService {

    List<Product> getListOfClothes(final HttpServletRequest request);

    int getNumberOfProductsShownOnPage(final String page, final int clothesSize);

    List<Product> getFavoriteProducts(final List<Integer> idsOfProductsWhichAreFavorite);

    boolean isFavoriteProduct(final List<Integer> idsOfProductsWhichAreFavorite, final Product product);

    boolean isProductInShoppingBag(final List<Product> shoppingBag, final Product product);

}
