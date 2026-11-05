package com.smarthire.controller;

import org.springframework.web.bind.annotation.*;

import com.smarthire.dto.RegisterRequest;
import com.smarthire.dto.LoginRequest;
import com.smarthire.dto.LoginResponse;
import com.smarthire.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return userService.registerUser(request);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
    return userService.loginUser(request);

}   
    }
