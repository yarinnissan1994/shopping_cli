package com.shopping_cli.server.service;

import com.shopping_cli.server.model.User;
import com.shopping_cli.server.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    private static final String USER_SESSION_ATTRIBUTE = "user";

    public List<User> findAll() {
        try {
            return userRepo.findAll();
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving all users: " + e.getMessage());
            throw e;
        }
    }

    public Optional<User> findById(int id) {
        try {
            return userRepo.findById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving user with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public boolean existsById(int id) {
        try {
            return userRepo.existsById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while checking existence of user with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public void save(User user) {
        try {
            userRepo.save(user);
        } catch (Exception e) {
            System.err.println("Error occurred while saving user: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(int id) {
        try {
            userRepo.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error occurred while deleting user with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public boolean existsByEmail(User user) {
        try {
            return userRepo.existsByEmail(user.getEmail());
        } catch (Exception e) {
            System.err.println("Error occurred while checking existence of user by email: " + e.getMessage());
            throw e;
        }
    }

    public boolean register(HttpSession session, User user) {
        try {
            if (userRepo.existsByEmail(user.getEmail()))
                return false;

            userRepo.save(user);
            return login(user.getEmail(), user.getPassword(), session);
        } catch (Exception e) {
            System.err.println("Error occurred while registering user: " + e.getMessage());
            throw e;
        }
    }

    public boolean login(String email, String password, HttpSession session) {
        try {
            User user = userRepo.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                getOrCreateUserSession(session, email);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error occurred while user login: " + e.getMessage());
            throw e;
        }
    }

    public User getCurrentUser(HttpSession session) {
        try {
            return getOrCreateUserSession(session, "");
        } catch (Exception e) {
            System.err.println("Error occurred while retrieving current user: " + e.getMessage());
            throw e;
        }
    }

    private User getOrCreateUserSession(HttpSession session, String userEmail) {
        try {
            User user = (User) session.getAttribute(USER_SESSION_ATTRIBUTE);
            if (user == null) {
                user = userRepo.findByEmail(userEmail);
                session.setAttribute(USER_SESSION_ATTRIBUTE, user);
            }
            System.out.println("session attribute: " + (User) session.getAttribute("user"));
            return user;
        } catch (Exception e) {
            System.err.println("Error occurred while getting or creating user session: " + e.getMessage());
            throw e;
        }
    }
}
