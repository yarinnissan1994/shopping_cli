package com.shopping_cli.server.repository;

import com.shopping_cli.server.model.User;
import com.shopping_cli.server.model.UserType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserCollectionRepository {
    private final List<User> users = new ArrayList<>();

    public UserCollectionRepository(){}

    public List<User> findAll(){
        return users;
    }

    public Optional<User> findById(long id){
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public boolean existsById(long id) {
        return users.stream().anyMatch(user -> user.getId() == id);
    }

    public void save(User user) {
        users.removeIf(u -> u.getId() == user.getId());
        users.add(user);
    }

    public void deleteById(long id) {
        users.removeIf(user -> user.getId() == id);
    }

    @PostConstruct
    public void init() {
        users.add(new User(1, "admin", "admin", "XXXXXXXXXXXXXXX", UserType.ADMIN));
        users.add(new User(2, "user", "user", "XXXXXXXXXXXXXX", UserType.CUSTOMER));
    }

}
