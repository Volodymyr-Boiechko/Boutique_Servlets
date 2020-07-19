package com.boiechko.entity;

import java.util.Objects;

public class Product {

    private int idProduct;
    private String typeName;
    private String productName;
    private String sex;
    private String brand;
    private String model;
    private String size;
    private String color;
    private String image;
    private int price;

    public Product(){
    }


    public Product
            (int idProduct, String typeName, String productName, String sex, String brand, String model,
             String size, String color, String image, int price)
    {
        this.idProduct = idProduct;
        this.typeName = typeName;
        this.productName = productName;
        this.sex = sex;
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.color = color;
        this.image = image;
        this.price = price;
    }

    public Product
            (String typeName, String productName, String sex, String brand, String model, String size,
             String color, String image, int price)
    {
        this.typeName = typeName;
        this.productName = productName;
        this.sex = sex;
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.color = color;
        this.image = image;
        this.price = price;
    }

    public int getIdProduct() { return idProduct; }

    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    public String getTypeName() { return typeName; }

    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idProduct == product.idProduct &&
                price == product.price &&
                typeName.equals(product.typeName) &&
                productName.equals(product.productName) &&
                sex.equals(product.sex) &&
                brand.equals(product.brand) &&
                Objects.equals(model, product.model) &&
                size.equals(product.size) &&
                Objects.equals(color, product.color) &&
                image.equals(product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, typeName, productName, sex, brand, model, size, color, image, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", typeName='" + typeName + '\'' +
                ", productName='" + productName + '\'' +
                ", sex='" + sex + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
