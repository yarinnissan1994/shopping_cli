package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.Category;
import com.shopping_cli.server.model.Product;
import com.shopping_cli.server.service.CategoryService;
import com.shopping_cli.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.findAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        try {
            Product product = productService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
            return ResponseEntity.ok(product);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        try {
            Optional<Category> category = categoryService.findById(product.getCategoryId());
            if (!category.isPresent())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
            product.setCategory(category.get());
            productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.err.println("Error occurred while creating product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            if (!productService.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
            else {
                productService.save(product);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error occurred while updating product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        try {
            if (!productService.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
            else {
                productService.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error occurred while deleting product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
