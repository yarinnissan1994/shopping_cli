package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.OrderItem;
import com.shopping_cli.server.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("")
    public List<OrderItem> getAllOrderItems(){
        return orderItemService.findAll();
    }
}
