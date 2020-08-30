package com.boiechko.service.interfaces;

import com.boiechko.entity.Order;
import com.boiechko.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderService {

    boolean addOrder(final Order order);

    List<Order> getAllOrdersByPersonId(final int id);

    int getLastId();

    boolean checkIfAddressHasOrder(final int id);

    Map<Order, List<Product>> getAllOrdersAndTheirProducts(final int idUser);

    Map.Entry<Order, List<Product>> getOrderAndHisProducts(final int idPerson, final String idOrder);

}
