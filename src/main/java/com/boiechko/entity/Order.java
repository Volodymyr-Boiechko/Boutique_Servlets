package com.boiechko.entity;

import java.sql.Date;
import java.util.Objects;

public class Order {

    private int idOrder;
    private int idPerson;
    private int totalPrice;
    private Date timeOrder;

    public Order(){
    }

    public Order(int idPerson, int totalPrice, Date timeOrder) {
        this.idPerson = idPerson;
        this.totalPrice = totalPrice;
        this.timeOrder = timeOrder;
    }

    public int getIdOrder() { return idOrder; }

    public void setIdOrder(int idOrder) { this.idOrder = idOrder; }

    public int getIdPerson() { return idPerson; }

    public void setIdPerson(int idPerson) { this.idPerson = idPerson; }

    public int getTotalPrice() { return totalPrice; }

    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

    public Date getTimeOrder() { return timeOrder; }

    public void setTimeOrder(Date timeOrder) { this.timeOrder = timeOrder; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder &&
                idPerson == order.idPerson &&
                totalPrice == order.totalPrice &&
                timeOrder.equals(order.timeOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idPerson, totalPrice, timeOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idPerson=" + idPerson +
                ", totalPrice=" + totalPrice +
                ", timeOrder='" + timeOrder + '\'' +
                '}';
    }
}