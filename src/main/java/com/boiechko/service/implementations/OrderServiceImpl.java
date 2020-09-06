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
    public List<Order> getAllOrdersByPersonId(final int idPerson) { return orderDao.getAllOrdersByPersonId(idPerson); }

    @Override
    public int getIdOfLastAddedOrder() { return orderDao.getLastId(); }

    @Override
    public boolean isAddressHasOrder(final int idAddress) {

        return orderDao.getAllOrdersByAddressId(idAddress).size() == 0;
    }

    @Override
    public Map<Order, List<Product>> getAllOrdersAndTheirProducts(final int idPerson) {

        final Map<Order, List<Product>> allOrdersAndTheirProductsOfPerson =
                orderDao.getAllOrdersAndTheirProductsOfPerson(idPerson);

        Map<Order, List<Product>> treeMap = new TreeMap<>(
                (o1, o2) -> Integer.compare(o2.getIdOrder(), o1.getIdOrder())
        );

        treeMap.putAll(allOrdersAndTheirProductsOfPerson);

        return treeMap;

    }

    @Override
    public Map.Entry<Order, List<Product>> getOrderAndHisProducts(final int idPerson, final String idOrder) {

        Map<Order, List<Product>> orderAndItsProductsOfPerson =
                orderDao.getOrderAndItsProductsOfPerson(idPerson, Integer.parseInt(idOrder));

        return orderAndItsProductsOfPerson.entrySet().iterator().next();

    }

}
