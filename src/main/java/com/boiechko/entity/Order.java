package com.boiechko.entity;

import java.util.Objects;

public class Order {

    private int idOrder;
    private int idPerson;
    private int idProduct;
    private int quantity;
    private String time;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder &&
                idPerson == order.idPerson &&
                idProduct == order.idProduct &&
                quantity == order.quantity &&
                time.equals(order.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idPerson, idProduct, quantity, time);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idPerson=" + idPerson +
                ", idProduct=" + idProduct +
                ", quantity=" + quantity +
                ", time='" + time + '\'' +
                '}';
    }
}