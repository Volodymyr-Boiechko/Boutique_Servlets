package com.boiechko.service.interfaces;

import com.boiechko.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClothesService {

    List<Product> getNewest();

    List<Product> getUniqueFields(final String uniqueColumn, final String condition, final String statement);

    List<Product> groupBy(final String column);

    List<Product> getPopularBrands();

    List<Product> getListOfClothes(final HttpServletRequest request);

    String getAmountOfProducts(final String page, final int clothesSize);

    String getNumberOfProductsOnPage(final String page);

    List<Product> getFavoriteClothes(final List<Integer> favoriteId);

    boolean isInFavorite(final List<Integer> favoriteId, final Product product);

    boolean isInShoppingBag(final List<Product> shoppingBag, final Product product);


}
