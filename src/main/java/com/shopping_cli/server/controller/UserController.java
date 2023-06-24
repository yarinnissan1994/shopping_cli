package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.User;
import com.shopping_cli.server.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "User not found!"));
    }

    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.existsByEmail(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User already exists");
        }

        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created successfully");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        if (!userService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        else
            userService.save(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        if (!userService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        else
            userService.deleteById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user, HttpSession session) {
        if (userService.existsByEmail(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User already exists");
        }

        userService.register(session, user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {
        boolean userFound = userService.login(user.getEmail(), user.getPassword(),  session);
        if (!userFound)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Incorrect information");
        else
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully logged in");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully logged out");
    }

    @GetMapping("/current")
    public User getCurrentUser(HttpSession session) {
        return userService.getCurrentUser(session);
    }
}
