package com.shopping_cli.server.service;

import com.shopping_cli.server.model.Category;
import com.shopping_cli.server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            System.err.println("Failed to retrieve all categories: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Category> findById(int id) {
        try {
            return categoryRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Failed to find category by ID: " + id + ", Error: " + e.getMessage());
            throw e;
        }
    }

    public boolean existsById(int id) {
        try {
            return categoryRepository.existsById(id);
        } catch (Exception e) {
            System.err.println("Failed to check if category exists by ID: " + id + ", Error: " + e.getMessage());
            throw e;
        }
    }

    public void save(Category category) {
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            System.err.println("Failed to save category: " + category + ", Error: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(int id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Failed to delete category by ID: " + id + ", Error: " + e.getMessage());
            throw e;
        }
    }
}
