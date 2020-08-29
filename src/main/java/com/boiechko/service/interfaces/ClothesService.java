package com.boiechko.service.interfaces;

import com.boiechko.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClothesService {

    List<Product> getListOfClothes(final HttpServletRequest request);

    String getAmountOfProducts(final String page, final int clothesSize);

    String getNumberOfProductsOnPage(final String page);



}
