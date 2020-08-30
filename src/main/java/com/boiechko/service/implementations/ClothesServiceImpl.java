package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.ProductDaoImpl;
import com.boiechko.dao.interfaces.ProductDao;
import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ClothesService;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ClothesServiceImpl implements ClothesService {

    private final ProductService productService = new ProductServiceImpl();
    private final ProductDao productDao = new ProductDaoImpl();

    private final int amountProductsInPage = 12;

    @Override
    public List<Product> getNewest() {
        return productDao.getNewest();
    }

    @Override
    public List<Product> getUniqueFields(String uniqueColumn, String condition, String statement) {
        return productDao.getUniqueFields(uniqueColumn, condition, statement);
    }

    @Override
    public List<Product> groupBy(String column) {
        return productDao.groupBy(column);
    }

    @Override
    public List<Product> getPopularBrands() {

        final List<Product> allBrands = groupBy("brand");
        List<Product> brands = new ArrayList<>();

        for (Product product : allBrands) {

            List<Product> count = productService.getAllByCredentials("brand", product.getBrand());

            if (count.size() >= 10)
                brands.add(product);

        }

        return brands;

    }

    @Override
    public List<Product> getListOfClothes(final HttpServletRequest request) {

        final String[] blocks = request.getRequestURI().split("/");

        List<Product> clothes;

        switch (blocks[2]) {
            case "clothes":
            case "shoes":
            case "accessories":
            case "sportWear": {

                final String productName = request.getParameter("productName");

                if (productName == null)
                    clothes = productService.getAllByCredentials("typeName", getTypeName(blocks[2]));
                else
                    clothes = productService.getAllByCredentials("productName", productName);

                break;
            }
            case "newestClothes": {

                clothes = getNewest();
                break;
            }
            case "brands": {

                final String brand = request.getParameter("brand");
                clothes = productService.getAllByCredentials("brand", brand);
                break;
            }
            default:
                clothes = new ArrayList<>();

        }

        return clothes;

    }

    @Override
    public String getAmountOfProducts(final String page, final int clothesSize) {

        int count = Integer.parseInt(page) * amountProductsInPage - 1;

        if (clothesSize == 0)
            return Integer.toString(0);
        else if (count >= clothesSize)
            count = clothesSize - 1;

        return Integer.toString(count);
    }

    @Override
    public String getNumberOfProductsOnPage(final String page) {

        return Integer.toString(Integer.parseInt(page) * amountProductsInPage);
    }

    @Override
    public List<Product> getFavoriteClothes(List<Integer> favoriteId) {

        List<Product> favorite = new ArrayList<>();
        for (Integer id : favoriteId)
            favorite.add(productService.getProductById(id));

        return favorite;

    }

    @Override
    public boolean isInFavorite(List<Integer> favoriteId, Product product) {

        boolean isInFavorite = false;

        for (Integer elementId : favoriteId)
            if (elementId == product.getIdProduct()) {
                isInFavorite = true;
                break;
            }

        return isInFavorite;

    }

    @Override
    public boolean isInShoppingBag(List<Product> shoppingBag, Product product) {

        boolean isInBag = false;

        for (Product el : shoppingBag)
            if (el.equals(product)) {
                isInBag = true;
                break;
            }

        return isInBag;
    }

    private String getTypeName(final String name) {

        switch (name) {
            case "clothes":
                return "Одяг";
            case "shoes":
                return "Взуття";
            case "accessories":
                return "Аксесуари";
            case "sportWear":
                return "Спортивний одяг";
            default:
                return null;

        }
    }
}