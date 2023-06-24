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

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(int id){
        return userRepo.findById(id);
    }

    public boolean existsById(int id) {
        return userRepo.existsById(id);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void deleteById(int id) {
        userRepo.deleteById(id);
    }

    public boolean existsByEmail(User user) {
        return userRepo.existsByEmail(user.getEmail());
    }

    public boolean register(HttpSession session, User user) {
        if(userRepo.existsByEmail(user.getEmail())) return false;

        userRepo.save(user);

        return login(user.getEmail(), user.getPassword(), session);
    }

    public boolean login(String email, String password, HttpSession session) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            getOrCreateUserSession(session, email);
            return true;
        }
        return false;
    }

    public User getCurrentUser(HttpSession session) {
        return getOrCreateUserSession(session, "");
    }

    private User getOrCreateUserSession(HttpSession session, String userEmail) {
        User user = (User) session.getAttribute(USER_SESSION_ATTRIBUTE);
        if (user == null) {
            user = userRepo.findByEmail(userEmail);
            session.setAttribute(USER_SESSION_ATTRIBUTE, user);
        }
        return user;
    }
}
