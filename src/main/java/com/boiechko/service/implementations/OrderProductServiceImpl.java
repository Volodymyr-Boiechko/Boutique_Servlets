package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.OrderProductDaoImpl;
import com.boiechko.dao.interfaces.OrderProductDao;
import com.boiechko.entity.OrderProduct;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.OrderProductService;

import java.util.List;

public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductDao orderProductDao = new OrderProductDaoImpl();

    @Override
    public boolean addOrderProduct(final int idOrder, final String[] arrayOfProductsQuantities, final List<Product> shoppingBag) {

        for (int i = 0; i < shoppingBag.size(); i++) {

            OrderProduct orderProduct = new OrderProduct(idOrder, shoppingBag.get(i).getIdProduct(),
                    Integer.parseInt(arrayOfProductsQuantities[i]));

            shoppingBag.get(i).setQuantity(Integer.parseInt(arrayOfProductsQuantities[i]));

            if (!orderProductDao.add(orderProduct)) {
                return false;
            }

        }
        return true;

    }
}
