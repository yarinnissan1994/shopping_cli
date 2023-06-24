package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.Category;
import com.shopping_cli.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = categoryService.findAll();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving categories: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        try {
            Category category = categoryService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));
            return ResponseEntity.ok(category);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<Void> createCategory(@RequestBody Category category) {
        try {
            categoryService.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println("Error occurred while creating category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable int id, @RequestBody Category category) {
        try {
            if (!categoryService.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
            else {
                categoryService.save(category);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Error occurred while updating category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        try {
            if (!categoryService.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
            else {
                categoryService.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Error occurred while deleting category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
