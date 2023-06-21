package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.Product;
import com.shopping_cli.server.model.User;
import com.shopping_cli.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/Products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Product not found!"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createProduct(@RequestBody Product product){
        productService.save(product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (!productService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        else
            productService.save(product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        if (!productService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        else
            productService.deleteById(id);
    }
}
