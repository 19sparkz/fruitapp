package com.example.rgfshop.controller;

import com.example.rgfshop.model.User;
import com.example.rgfshop.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        User saved = userService.register(user.getUsername(), user.getPassword());
        return (saved != null) ? "Registered" : "Username already exists";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        boolean success = userService.login(user.getUsername(), user.getPassword());
        return success ? "Login successful" : "Invalid credentials";
    }

}
