package com.shopping_cli.server.service;

import com.shopping_cli.server.model.Product;
import com.shopping_cli.server.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private static final String CART_SESSION_ATTRIBUTE = "cart";

    public void addToCart(HttpSession session, Product product) {
        List<Product> cartProducts = getOrCreateCartSession(session);
        cartProducts.add(product);
    }

    public void removeFromCart(HttpSession session, int product) {
        List<Product> cartProducts = getOrCreateCartSession(session);
        cartProducts.remove(product);
    }

    public List<Product> getCart(HttpSession session) {
        return getOrCreateCartSession(session);
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_ATTRIBUTE);
    }

    public void updateCart(HttpSession session, List<Product> cartProducts) {
        session.setAttribute(CART_SESSION_ATTRIBUTE, cartProducts);
    }


    public void updateCart(HttpSession session, Product product) {
        List<Product> cartProducts = getOrCreateCartSession(session);
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getId() == product.getId()) {
                cartProducts.set(i, product);
                return;
            }
        }
        cartProducts.add(product);
    }

    public double getTotal(HttpSession session) {
        List<Product> cartProducts = getOrCreateCartSession(session);
        double total = 0;
        for (Product product : cartProducts) {
            total += product.getPrice();
        }
        return total;
    }

    private List<Product> getOrCreateCartSession(HttpSession session) {
        List<Product> cartProducts = (List<Product>) session.getAttribute(CART_SESSION_ATTRIBUTE);
        if (cartProducts == null) {
            cartProducts = new ArrayList<>();
            session.setAttribute(CART_SESSION_ATTRIBUTE, cartProducts);
        }
        return cartProducts;
    }

}
