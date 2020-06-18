package com.boiechko.entity;

import java.util.Objects;

public class Product {

    private int idProduct;
    private String name;
    private String brand;
    private String size;
    private String color;
    private String image;
    private double price;

    public Product(){
    }

    public Product(int idProduct, String name, String brand, String size, String color, String image, double price) {
        this.idProduct = idProduct;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.image = image;
        this.price = price;
    }

    public Product(String name, String brand, String size, String color, String image, double price) {
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.image = image;
        this.price = price;
    }

    public int getIdProduct() { return idProduct; }

    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idProduct == product.idProduct &&
                Double.compare(product.price, price) == 0 &&
                name.equals(product.name) &&
                brand.equals(product.brand) &&
                size.equals(product.size) &&
                Objects.equals(color, product.color) &&
                image.equals(product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, name, brand, size, color, image, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
