package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.ProductDaoImpl;
import com.boiechko.dao.interfaces.ProductDao;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final String PATH_TO_DATABASE_IMAGES = "C:\\Users\\volod\\IdeaProjects\\Boutique_Servlets\\web\\dataBaseImages\\";
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> getLatestAddedProducts() { return productDao.getLatestAddedProducts(); }

    @Override
    public List<Product> groupByColumnWithCondition(String condition, String statement, String column) {
        return productDao.groupByColumnWithCondition(condition, statement, column);
    }

    @Override
    public List<Product> groupByColumn(String column) { return productDao.groupByColumn(column); }

    @Override
    public List<Product> getPopularBrands() {

        return groupByColumn("brand").stream()
                .map(Product::getBrand)
                .map(brand -> getProductByColumn("brand", brand))
                .filter(products -> products.size() >= 10)
                .map(products -> products.get(0))
                .sorted(Comparator.comparing(Product::getBrand))
                .collect(Collectors.toList());

    }

    @Override
    public boolean addProduct(final Product product) {
        return productDao.add(product);
    }

    @Override
    public Product getProductById(final int idProduct) {
        return productDao.getById(idProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @Override
    public boolean updateProduct(final Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean deleteProduct(final int idProduct) {
        return productDao.delete(idProduct);
    }

    @Override
    public List<Product> getProductByColumn(final String column, final String credentials) {
        return productDao.getProductByColumn(column, credentials);
    }

    @Override
    public boolean saveImage(final Part image, final String destination) {

        final String imagePath = PATH_TO_DATABASE_IMAGES + destination.replace("/", "\\");

        final File fileDir = new File(imagePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        final String imageName = image.getSubmittedFileName();

        if (validateImage(imageName)) {
            try {
                image.write(imagePath + File.separator + imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }

        return true;

    }

    private boolean validateImage(final String imageName) {

        final String fileExt = imageName.substring(imageName.length() - 3);
        return "jpg".equals(fileExt) || "png".equals(fileExt) || "gif".equals(fileExt);
    }

    @Override
    public String getDestinationOfImage(final Part image, final String destination) {
        return destination + "/" + image.getSubmittedFileName();
    }

    @Override
    public List<String> getPathToProduct(final HttpServletRequest request, final Product product) {

        final String[] urlParts = request.getRequestURI().split("/");
        final String sex = urlParts[1].equals("manClothes") ? "Чоловіче" : "Жіноче";

        return new ArrayList<>(Arrays.asList(sex, getTypeName(product.getTypeName())));

    }

    private String getTypeName(final String name) {

        switch (name) {
            case "Одяг":
                return "clothes";
            case "Взуття":
                return "shoes";
            case "Аксесуари":
                return "accessories";
            case "Спортивний одяг":
                return "sportWear";
            default:
                return null;

        }
    }
}
