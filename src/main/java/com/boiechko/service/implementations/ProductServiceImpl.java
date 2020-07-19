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
    public List<Product> getUniqueFields(String uniqueColumn, String condition, String statement) {
        return productDao.getUniqueFields(uniqueColumn, condition, statement);
    }

    @Override
    public List<Product> groupBy(String column) { return productDao.groupBy(column); }

    @Override
    public boolean add(Product product) { return productDao.add(product); }

    @Override
    public Product getById(int id) { return productDao.getById(id); }

    @Override
    public List<Product> getAll() { return productDao.getAll(); }

    @Override
    public boolean update(Product product) { return productDao.update(product); }

    @Override
    public boolean delete(int id) { return productDao.delete(id); }
}
