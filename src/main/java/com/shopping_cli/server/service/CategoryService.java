package com.shopping_cli.server.service;

import com.shopping_cli.server.model.Category;
import com.shopping_cli.server.model.OrderItem;
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
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    public boolean existsById(int id) {
        return categoryRepository.existsById(id);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

}
