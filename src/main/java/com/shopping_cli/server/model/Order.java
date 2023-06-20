package com.shopping_cli.server.model;

import java.util.Date;

public class Order {
    private final long id;
    private final long userId;
    private final Date orderDate;
    private final double totalAmount;

    public Order(long id, long userId, Date orderDate, double totalAmount) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
