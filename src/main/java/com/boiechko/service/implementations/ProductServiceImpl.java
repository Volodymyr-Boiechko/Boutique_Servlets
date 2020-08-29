package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.ProductDaoImpl;
import com.boiechko.dao.interfaces.ProductDao;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean addProduct(Product product) { return productDao.add(product); }

    @Override
    public Product getProductById(int id) { return productDao.getById(id); }

    @Override
    public List<Product> getAllProducts() { return productDao.getAll(); }

    @Override
    public boolean updateProduct(Product product) { return productDao.update(product); }

    @Override
    public boolean deleteProduct(int id) { return productDao.delete(id); }

    @Override
    public List<Product> getAllByCredentials(String column, String credentials) { return productDao.getAllByCredentials(column, credentials); }

    @Override
    public List<Product> getNewest() { return productDao.getNewest(); }

    @Override
    public List<Product> getUniqueFields(String uniqueColumn, String condition, String statement) {
        return productDao.getUniqueFields(uniqueColumn, condition, statement);
    }

    @Override
    public List<Product> groupBy(String column) { return productDao.groupBy(column); }
}
