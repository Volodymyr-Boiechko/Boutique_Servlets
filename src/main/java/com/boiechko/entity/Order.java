package com.boiechko.entity;

public class Order {

    private int idOrder;
    private int idPerson;
    private int idProduct;
    private int quantity;
    private String time;
    private String activationCode;

    public Order(){
    }

    public Order(int idOrder, int idPerson, int idProduct, int quantity, String time) {
        this.idOrder = idOrder;
        this.idPerson = idPerson;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.time = time;
    }

    public Order(int idPerson, int idProduct, int quantity, String time) {
        this.idPerson = idPerson;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.time = time;
    }

    public int getIdOrder() { return idOrder; }

    public void setIdOrder(int idOrder) { this.idOrder = idOrder; }

    public int getIdPerson() { return idPerson; }

    public void setIdPerson(int idPerson) { this.idPerson = idPerson; }

    public int getIdProduct() { return idProduct; }

    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getActivationCode() { return activationCode; }

    public void setActivationCode(String activationCode) { this.activationCode = activationCode; }
}