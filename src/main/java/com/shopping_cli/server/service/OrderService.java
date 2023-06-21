package com.shopping_cli.server.service;

import com.shopping_cli.server.model.Order;
import com.shopping_cli.server.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Optional<Order> findById(int id) {
        return orderRepo.findById(id);
    }

    public boolean existsById(int id) {
        return orderRepo.existsById(id);
    }

    public void save(Order order) {
        orderRepo.save(order);
    }

    public void deleteById(int id) {
        orderRepo.deleteById(id);
    }
}
