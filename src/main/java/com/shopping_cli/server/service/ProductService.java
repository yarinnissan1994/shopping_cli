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

    public List<Product> findAll(){
        return productRepo.findAll();
    }

    public Optional<Product> findById(int id){
        return productRepo.findById(id);
    }

    public boolean existsById(int id) {
        return productRepo.existsById(id);
    }

    public void save(Product product) {
        productRepo.save(product);
    }

    public void deleteById(int id) {
        productRepo.deleteById(id);
    }

}
