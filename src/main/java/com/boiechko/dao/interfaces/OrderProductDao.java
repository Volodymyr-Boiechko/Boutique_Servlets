package com.boiechko.dao.interfaces;

import com.boiechko.entity.OrderProduct;

import java.util.List;

public interface OrderProductDao {

    boolean add(OrderProduct orderProduct);

    List<OrderProduct> getAllByIdOrder(int id);

}
