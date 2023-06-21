package com.shopping_cli.server.service;

import com.shopping_cli.server.model.Order;
import com.shopping_cli.server.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}
