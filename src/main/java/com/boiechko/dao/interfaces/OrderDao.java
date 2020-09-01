package com.boiechko.dao.interfaces;

import com.boiechko.entity.Order;
import com.boiechko.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    boolean add(final Order order);

    List<Order> getAllOrdersByPersonId(final int idPerson);

    int getLastId();

    List<Order> getAllOrdersByAddressId(final int idAddress);

    Map<Order, List<Product>> getAllOrdersAndTheirProductsOfPerson(final int idPerson);

    Map<Order, List<Product>> getOrderAndItsProductsOfPerson(final int idPerson, final int idOrder);

}
