package com.boiechko.service.interfaces;

import com.boiechko.entity.Order;
import com.boiechko.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderService{

    boolean addOrder(Order order);

    List<Order> getAllOrdersByPersonId(int id);

    int getLastId();

    Map<Order, List<Product>> getAllOrdersAndTheirProducts(int idUser);

    Map<Order, List<Product>> getOrderAndHisProducts(int idUser, int idOrder);

}
