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
        try {
            return orderItemRepo.findAll();
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving all order items: " + e.getMessage());
            throw e;
        }
    }

    public Optional<OrderItem> findById(int id) {
        try {
            return orderItemRepo.findById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving order item with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public boolean existsById(int id) {
        try {
            return orderItemRepo.existsById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while checking existence of order item with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public void save(OrderItem orderItem) {
        try {
            orderItemRepo.save(orderItem);
        } catch (Exception e) {
            System.err.println("Error occurred while saving order item: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(int id) {
        try {
            orderItemRepo.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while deleting order item with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}

