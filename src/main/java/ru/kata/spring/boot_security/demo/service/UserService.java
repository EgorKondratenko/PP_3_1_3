package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findByName(String username);
    User getUserById(Long id);
    boolean saveUser(User user);
    boolean deleteUserById(Long id);
    void updateUser(User user);
    List<User> findAll();
}
