package com.boiechko.service.interfaces;

import com.boiechko.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.List;

public interface ProductService {

    boolean addProduct(final Product product);

    Product getProductById(final int id);

    List<Product> getAllProducts();

    boolean updateProduct(final Product product);

    boolean deleteProduct(final int id);

    List<Product> getAllByCredentials(final String column, final String credentials);

    boolean saveImage(final Part image, final String destination);

    String getDestinationOfImage(final Part image, final String destination);

    List<String> getPathToProduct(final HttpServletRequest request, final Product product);

}
