package com.shopping_cli.server.service;

import com.shopping_cli.server.model.User;
import com.shopping_cli.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

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
}
