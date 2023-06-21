package com.shopping_cli.server.service;

import com.shopping_cli.server.model.Product;
import com.shopping_cli.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public List<Product> findAll(){
        return productRepo.findAll();
    }
}