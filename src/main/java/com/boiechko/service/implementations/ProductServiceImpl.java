package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.ProductDaoImpl;
import com.boiechko.dao.interfaces.ProductDao;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean addProduct(final Product product) { return productDao.add(product); }

    @Override
    public Product getProductById(final int id) { return productDao.getById(id); }

    @Override
    public List<Product> getAllProducts() { return productDao.getAll(); }

    @Override
    public boolean updateProduct(final Product product) { return productDao.update(product); }

    @Override
    public boolean deleteProduct(final int id) { return productDao.delete(id); }

    @Override
    public List<Product> getAllByCredentials(final String column, final String credentials) {
        return productDao.getAllByCredentials(column, credentials);
    }

    @Override
    public List<Product> getNewest() { return productDao.getNewest(); }

    @Override
    public List<Product> getUniqueFields(final String uniqueColumn, final String condition, final String statement) {
        return productDao.getUniqueFields(uniqueColumn, condition, statement);
    }

    @Override
    public List<Product> groupBy(final String column) { return productDao.groupBy(column); }
}
