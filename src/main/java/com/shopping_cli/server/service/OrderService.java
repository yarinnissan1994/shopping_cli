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
        try {
            return orderRepo.findAll();
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving all orders: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Order> findById(int id) {
        try {
            return orderRepo.findById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving order with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public boolean existsById(int id) {
        try {
            return orderRepo.existsById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while checking existence of order with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public void save(Order order) {
        try {
            orderRepo.save(order);
        } catch (Exception e) {
            System.err.println("Error occurred while saving order: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(int id) {
        try {
            orderRepo.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while deleting order with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
