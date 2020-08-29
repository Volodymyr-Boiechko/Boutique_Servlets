package com.boiechko.service.implementations;

import com.boiechko.entity.Product;
import com.boiechko.service.interfaces.ClothesService;
import com.boiechko.service.interfaces.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ClothesServiceImpl implements ClothesService {

    private final ProductService productService = new ProductServiceImpl();

    private final int amountProductsInPage = 12;

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

                clothes = productService.getNewest();
                break;
            }
            case "brands": {

                final String brand = request.getParameter("brand");
                clothes = productService.getAllByCredentials("brand", brand);
                break;
            }
            default: {

                clothes = new ArrayList<>();

            }
        }

        return clothes;

    }

    @Override
    public String getAmountOfProducts(final String page, final int clothesSize) {

        int count = Integer.parseInt(page) * amountProductsInPage - 1;

        if (count >= clothesSize)
            count = clothesSize - 1;

        return Integer.toString(count);
    }

    @Override
    public String getNumberOfProductsOnPage(final String page) {

        return Integer.toString( Integer.parseInt(page) * amountProductsInPage);
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