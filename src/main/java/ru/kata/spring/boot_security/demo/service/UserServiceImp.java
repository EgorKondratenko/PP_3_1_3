package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findByName(String username) {
        return userRepository.findByName(username);
    }
    @Override
    public User getUserById(Long id) {
        User user = userRepository.findByName(String.valueOf(id));
        if (id == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }
    @Override
    @Transactional
    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByName(user.getUsername());

        if (userFromDB != null) {
            return false;
        };
        userRepository.save(user);
        return true;
    }
    @Override
    @Transactional
    public boolean deleteUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
