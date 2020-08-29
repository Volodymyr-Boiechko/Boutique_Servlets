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

    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean addOrder(final Order order) { return orderDao.add(order); }

    @Override
    public List<Order> getAllOrdersByPersonId(final int id) { return orderDao.getAllById(id); }

    @Override
    public int getLastId() { return orderDao.getLastId(); }

    @Override
    public Map<Order, List<Product>> getAllOrdersAndTheirProducts(final int idUser) {

        final Map<Order, List<Product>> map = orderDao.getAllOrdersAndTheirProducts(idUser);

        Map<Order, List<Product>> treeMap = new TreeMap<>(
                (o1, o2) -> Integer.compare(o2.getIdOrder(), o1.getIdOrder())
        );

        treeMap.putAll(map);

        return treeMap;

    }

    @Override
    public Map<Order, List<Product>> getOrderAndHisProducts(final int idUser, final int idOrder) {
        return orderDao.getOrderAndHisProducts(idUser, idOrder);
    }

}
