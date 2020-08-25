package com.boiechko.service.implementations;

import com.boiechko.dao.interfaces.OrderProductDao;
import com.boiechko.entity.OrderProduct;
import com.boiechko.service.interfaces.OrderProductService;

import java.util.List;

public class OrderProductServiceImpl implements OrderProductService {

    OrderProductDao orderProductDao = new OrderProductServiceImpl();

    @Override
    public boolean add(OrderProduct orderProduct) { return orderProductDao.add(orderProduct); }

    @Override
    public List<OrderProduct> getAllByIdOrder(int id) { return orderProductDao.getAllByIdOrder(id); }
}
