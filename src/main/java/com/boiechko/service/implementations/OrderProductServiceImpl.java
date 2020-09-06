package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.OrderProductDaoImpl;
import com.boiechko.dao.interfaces.OrderProductDao;
import com.boiechko.entity.OrderProduct;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.OrderProductService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductDao orderProductDao = new OrderProductDaoImpl();

    @Override
    public boolean addOrderProduct(final int idOrder, final String[] arrayOfProductsQuantities, final List<Product> shoppingBag) {

        AtomicInteger indexOfProductQuantity = new AtomicInteger(0);

        return shoppingBag.stream()
                .peek(product -> product.setQuantity(Integer.parseInt(
                        arrayOfProductsQuantities[indexOfProductQuantity.getAndIncrement()])))
                .map(product -> new OrderProduct(idOrder, product.getIdProduct(),product.getQuantity()))
                .allMatch(orderProductDao::add);
    }
}