package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.OrderDaoImpl;
import com.boiechko.dao.interfaces.OrderDao;
import com.boiechko.entity.Order;
import com.boiechko.service.interfaces.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean add(Order order) { return orderDao.add(order); }

    @Override
    public List<Order> getAllById(int id) { return orderDao.getAllById(id); }
}
