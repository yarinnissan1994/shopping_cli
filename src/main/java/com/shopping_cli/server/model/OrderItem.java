package com.shopping_cli.server.model;

public class OrderItem {
    private final long id;
    private final long orderId;
    private final long productId;
    private final long quantity;
    private final double itemAmount;

    public OrderItem(long id, long orderId, long productId, long quantity, double itemAmount) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.itemAmount = itemAmount;
    }

    public long getId() {
        return id;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public double getItemAmount() {
        return itemAmount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", itemAmount=" + itemAmount +
                '}';
    }
}
