package com.boiechko.dao.interfaces;

import com.boiechko.entity.Product;

import java.util.List;

public interface ProductDao extends Dao<Product> {

    List<Product> getAllByCredentials(final String column, final String credentials);

    List<Product> getNewest();

    List<Product> getUniqueFields(final String uniqueColumn, final String condition, final String statement);

    List<Product> groupBy(final String column);

}