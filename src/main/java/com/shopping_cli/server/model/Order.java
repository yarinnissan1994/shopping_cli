package com.shopping_cli.server.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, updatable = false)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "order_date", nullable = false)
    private Date orderDate;
    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    public Order() {}

    public Order(User user, Date orderDate, double totalAmount) {
        this.user = user;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return user.getId();
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
                ", userId=" + user.getId() +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
