package com.shopping_cli.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false, updatable = false)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "item_amount", nullable = false)
    private double itemAmount;

    public OrderItem(Order order, Product product, int quantity, double itemAmount) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.itemAmount = itemAmount;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return order.getId();
    }

    public int getProductId() {
        return product.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemAmount() {
        return itemAmount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + order.getId() +
                ", productId=" + product.getId() +
                ", quantity=" + quantity +
                ", itemAmount=" + itemAmount +
                '}';
    }
}
