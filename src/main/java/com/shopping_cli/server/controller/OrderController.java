package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.Order;
import com.shopping_cli.server.model.User;
import com.shopping_cli.server.service.OrderService;
import com.shopping_cli.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<Order> getAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id){
        return orderService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Order not found!"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createOrder(@RequestBody Order order){
        Optional<User> user = userService.findById(order.getUserId());
        if (!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        order.setUser(user.get());
        orderService.save(order);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateOrder(@PathVariable int id, @RequestBody Order order) {
        if (!orderService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!");
        else
            orderService.save(order);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        if (!orderService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!");
        else
            orderService.deleteById(id);
    }
}
