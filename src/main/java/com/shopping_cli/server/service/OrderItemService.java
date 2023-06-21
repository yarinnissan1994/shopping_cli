package com.shopping_cli.server.service;

import com.shopping_cli.server.model.OrderItem;
import com.shopping_cli.server.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepo;

    public List<OrderItem> findAll() {
        return orderItemRepo.findAll();
    }
}
