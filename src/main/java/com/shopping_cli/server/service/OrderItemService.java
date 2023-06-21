package com.shopping_cli.server.service;

import com.shopping_cli.server.model.OrderItem;
import com.shopping_cli.server.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepo;

    public List<OrderItem> findAll() {
        return orderItemRepo.findAll();
    }

    public Optional<OrderItem> findById(int id) {
        return orderItemRepo.findById(id);
    }

    public boolean existsById(int id) {
        return orderItemRepo.existsById(id);
    }

    public void save(OrderItem orderItem) {
        orderItemRepo.save(orderItem);
    }

    public void deleteById(int id) {
        orderItemRepo.deleteById(id);
    }

}
