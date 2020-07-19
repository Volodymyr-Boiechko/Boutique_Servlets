package com.boiechko.dao.interfaces;

import com.boiechko.entity.Product;

import java.util.List;

public interface ProductDao extends Dao<Product> {

    List<Product> getAllByCredentials(String column, String credentials);

    List<Product> getNewest();

}