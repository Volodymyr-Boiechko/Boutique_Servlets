package com.boiechko.dao.interfaces;

import com.boiechko.entity.Order;
import com.boiechko.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    boolean add(Order order);

    List<Order> getAllById(int id);

    int getLastId();

    Map<Order, List<Product>> getAllOrdersAndTheirProducts(int idUser);

    Map<Order, List<Product>> getOrderAndHisProducts(int idUser, int idOrder);

}
