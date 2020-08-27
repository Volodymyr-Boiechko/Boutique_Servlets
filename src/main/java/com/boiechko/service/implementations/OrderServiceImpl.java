package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.OrderDaoImpl;
import com.boiechko.dao.interfaces.OrderDao;
import com.boiechko.entity.Order;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.OrderService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean add(Order order) { return orderDao.add(order); }

    @Override
    public List<Order> getAllById(int id) { return orderDao.getAllById(id); }

    @Override
    public int getLastId() { return orderDao.getLastId(); }

    @Override
    public Map<Order, List<Product>> getAllOrdersAndTheirProducts(int idUser) {

        Map<Order, List<Product>> map = orderDao.getAllOrdersAndTheirProducts(idUser);

        Map<Order, List<Product>> treeMap = new TreeMap<>(
                (o1, o2) -> Integer.compare(o2.getIdOrder(), o1.getIdOrder())
        );

        treeMap.putAll(map);

        return treeMap;

    }

    @Override
    public Map<Order, List<Product>> getOrderAndHisProducts(int idUser, int idOrder) {
        return orderDao.getOrderAndHisProducts(idUser, idOrder);
    }

}
