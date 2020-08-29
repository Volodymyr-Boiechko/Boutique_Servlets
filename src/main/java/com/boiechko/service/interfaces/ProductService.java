package com.boiechko.service.interfaces;

import com.boiechko.entity.Product;

import java.util.List;

public interface ProductService {

    boolean addProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);

    List<Product> getAllByCredentials(String column, String credentials);

    List<Product> getNewest();

    List<Product> getUniqueFields(String uniqueColumn, String condition, String statement);

    List<Product> groupBy(String column);

}
