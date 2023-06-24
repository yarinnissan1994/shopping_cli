package com.shopping_cli.server.service;

import com.shopping_cli.server.model.Product;
import com.shopping_cli.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public List<Product> findAll() {
        try {
            return productRepo.findAll();
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving all products: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Product> findById(int id) {
        try {
            return productRepo.findById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving product with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public boolean existsById(int id) {
        try {
            return productRepo.existsById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while checking existence of product with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public void save(Product product) {
        try {
            productRepo.save(product);
        } catch (Exception e) {
            System.err.println("Error occurred while saving product: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(int id) {
        try {
            productRepo.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while deleting product with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
