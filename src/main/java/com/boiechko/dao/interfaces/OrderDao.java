package com.boiechko.dao.interfaces;

import com.boiechko.entity.Order;
import com.boiechko.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    boolean add(final Order order);

    List<Order> getAllById(final int id);

    int getLastId();

    Map<Order, List<Product>> getAllOrdersAndTheirProducts(final int idUser);

    Map<Order, List<Product>> getOrderAndHisProducts(final int idUser, final int idOrder);

}
