package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.ProductDaoImpl;
import com.boiechko.dao.interfaces.ProductDao;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> getAllByCredentials(String column, String credentials) {
        return productDao.getAllByCredentials(column, credentials);
    }

    @Override
    public List<Product> getNewest() { return productDao.getNewest(); }

    @Override
    public boolean add(Product product) {
        return false;
    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
