package com.boiechko.dao.interfaces;

import com.boiechko.entity.Order;

import java.util.List;

public interface OrderDao {

    boolean add(Order order);

    List<Order> getAllById(int id);

}
