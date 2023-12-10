package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserByUsername(String username);
    User login(String username, String password);
}
