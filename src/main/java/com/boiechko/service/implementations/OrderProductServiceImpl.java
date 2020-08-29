package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.OrderProductDaoImpl;
import com.boiechko.dao.interfaces.OrderProductDao;
import com.boiechko.entity.OrderProduct;
import com.boiechko.service.interfaces.OrderProductService;

public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductDao orderProductDao = new OrderProductDaoImpl();

    @Override
    public boolean addOrderProduct(OrderProduct orderProduct) { return orderProductDao.add(orderProduct); }
}
