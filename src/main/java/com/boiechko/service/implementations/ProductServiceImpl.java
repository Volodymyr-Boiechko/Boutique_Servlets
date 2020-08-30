package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.ProductDaoImpl;
import com.boiechko.dao.interfaces.ProductDao;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final String appPath = "C:\\Users\\volod\\IdeaProjects\\Boutique_Servlets\\web\\dataBaseImages\\";
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
    public boolean saveImage(Part image, String destination) {

        final String imagePath = appPath + destination.replace("/", "\\");

        final File fileDir = new File(imagePath);
        if (!fileDir.exists())
            fileDir.mkdirs();

        final String imageName = image.getSubmittedFileName();

        if (validateImage(imageName)) {
            try {
                image.write(imagePath + File.separator + imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else return false;

        return true;

    }

    private boolean validateImage(final String imageName) {

        final String fileExt = imageName.substring(imageName.length() - 3);
        return "jpg".equals(fileExt) || "png".equals(fileExt) || "gif".equals(fileExt);
    }

    @Override
    public String getDestinationOfImage(Part image, String destination) {
        return destination + "/" + image.getSubmittedFileName();
    }
}
