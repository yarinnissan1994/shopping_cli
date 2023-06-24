package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.Product;
import com.shopping_cli.server.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getCurrentCart(HttpSession session) {
        try {
            List<Product> cart = cartService.getCart(session);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            System.err.println("Error occurred while getting current cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/clear")
    public ResponseEntity<Void> clearCart(HttpSession session) {
        try {
            cartService.clearCart(session);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error occurred while clearing cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotal(HttpSession session) {
        try {
            double total = cartService.getTotal(session);
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            System.err.println("Error occurred while calculating total: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addProductToCart(@RequestBody Product product, HttpSession session) {
        try {
            cartService.addToCart(session, product);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error occurred while adding product to cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable int productId, HttpSession session) {
        try {
            cartService.removeFromCart(session, productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error occurred while removing product from cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
