package com.boiechko.service.interfaces;

import com.boiechko.entity.Product;

import java.util.List;

public interface OrderProductService {

    boolean addOrderProduct(final int idOrder, final String[] selectedItems, final List<Product> shoppingBag);

}
