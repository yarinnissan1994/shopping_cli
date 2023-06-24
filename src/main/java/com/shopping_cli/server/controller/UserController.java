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
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving users: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        try {
            User user = userService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            return ResponseEntity.ok(user);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            if (userService.existsByEmail(user)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("User already exists");
            }

            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User created successfully");
        } catch (Exception e) {
            System.err.println("Error occurred while creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            if (!userService.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
            else {
                userService.save(user);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error occurred while updating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            if (!userService.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
            else {
                userService.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error occurred while deleting user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user, HttpSession session) {
        try {
            if (userService.existsByEmail(user)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("User already exists");
            }

            userService.register(session, user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User created successfully");
        } catch (Exception e) {
            System.err.println("Error occurred while registering user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {
        try {
            boolean userFound = userService.login(user.getEmail(), user.getPassword(), session);
            if (!userFound)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Wrong email or password");
            else
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("Successfully logged in");
        } catch (Exception e) {
            System.err.println("Error occurred while logging in: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        try {
            session.invalidate();
            return ResponseEntity.status(HttpStatus.OK).body("Successfully logged out");
        } catch (Exception e) {
            System.err.println("Error occurred while logging out: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        try {
            User user = userService.getCurrentUser(session);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving current user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
