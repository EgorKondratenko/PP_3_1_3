package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

@Controller
@RequestMapping("/admin")

public class AdminController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public AdminController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("users", userServiceImp.findAll());
        return "users";
    }

    @GetMapping("/createUser")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user) {
        userServiceImp.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userServiceImp.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("update", userServiceImp.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/updateUser/{getId}")
    public String saveUpdateUser(@PathVariable Long getId, @ModelAttribute("user") User user) {
        user.setId(getId);
        userServiceImp.updateUser(user);
        return "redirect:/admin";
    }
}
