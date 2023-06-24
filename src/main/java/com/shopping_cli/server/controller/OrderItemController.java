package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.Order;
import com.shopping_cli.server.model.OrderItem;
import com.shopping_cli.server.model.Product;
import com.shopping_cli.server.service.OrderItemService;
import com.shopping_cli.server.service.OrderService;
import com.shopping_cli.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        try {
            List<OrderItem> orderItems = orderItemService.findAll();
            return ResponseEntity.ok(orderItems);
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving order items: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable int id) {
        try {
            OrderItem orderItem = orderItemService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderItem not found!"));
            return ResponseEntity.ok(orderItem);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving order item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<Void> createOrderItem(@RequestBody OrderItem orderItem) {
        try {
            Optional<Order> order = orderService.findById(orderItem.getOrderId());
            if (!order.isPresent())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!");
            Optional<Product> product = productService.findById(orderItem.getProductId());
            if (!product.isPresent())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
            orderItem.setProduct(product.get());
            orderItemService.save(orderItem);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println("Error occurred while creating order item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderItem(@PathVariable int id, @RequestBody OrderItem orderItem) {
        try {
            if (!orderItemService.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderItem not found!");
            else {
                orderItemService.save(orderItem);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Error occurred while updating order item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int id) {
        try {
            if (!orderItemService.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderItem not found!");
            else {
                orderItemService.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Error occurred while deleting order item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
