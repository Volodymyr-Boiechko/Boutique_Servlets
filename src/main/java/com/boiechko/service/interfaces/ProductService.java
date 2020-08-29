package com.boiechko.service.interfaces;

import com.boiechko.entity.Product;

import java.util.List;

public interface ProductService {

    boolean addProduct(final Product product);

    Product getProductById(final int id);

    List<Product> getAllProducts();

    boolean updateProduct(final Product product);

    boolean deleteProduct(final int id);

    List<Product> getAllByCredentials(final String column, final String credentials);

    List<Product> getNewest();

    List<Product> getUniqueFields(final String uniqueColumn, final String condition, final String statement);

    List<Product> groupBy(final String column);

}
