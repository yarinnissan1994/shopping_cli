package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.Order;
import com.shopping_cli.server.model.OrderItem;
import com.shopping_cli.server.model.Product;
import com.shopping_cli.server.service.OrderItemService;
import com.shopping_cli.server.service.OrderService;
import com.shopping_cli.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<OrderItem> getAllOrderItems(){
        return orderItemService.findAll();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable int id){
        return orderItemService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "OrderItem not found!"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createOrderItem(@RequestBody OrderItem orderItem){
        Optional<Order> order = orderService.findById(orderItem.getOrderId());
        if (!order.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!");
        Optional<Product> product = productService.findById(orderItem.getProductId());
        if (!product.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        orderItem.setProduct(product.get());
        orderItemService.save(orderItem);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateOrderItem(@PathVariable int id, @RequestBody OrderItem orderItem) {
        if (!orderItemService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderItem not found!");
        else
            orderItemService.save(orderItem);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable int id) {
        if (!orderItemService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderItem not found!");
        else
            orderItemService.deleteById(id);
    }
}
