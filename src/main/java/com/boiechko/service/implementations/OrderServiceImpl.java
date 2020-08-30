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
    public boolean checkIfAddressHasOrder(int id) {

        List<Order> orders = orderDao.getAllByAddressId(id);
        return orders.size() == 0;
    }


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
    public Map.Entry<Order, List<Product>> getOrderAndHisProducts(final int idPerson, final String idOrder) {

        Map<Order, List<Product>> map = orderDao.getOrderAndHisProducts(idPerson, Integer.parseInt(idOrder));

        return map.entrySet().iterator().next();

    }



}
