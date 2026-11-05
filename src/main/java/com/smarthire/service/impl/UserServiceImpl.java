package com.smarthire.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smarthire.dto.LoginRequest;
import com.smarthire.dto.RegisterRequest;
import com.smarthire.entity.User;
import com.smarthire.repository.UserRepository;
import com.smarthire.service.UserService;
import com.smarthire.dto.LoginResponse;
import com.smarthire.security.JwtService;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String registerUser(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
        passwordEncoder.encode(request.getPassword())); 
        user.setRole(request.getRole());

        userRepository.save(user);

        return "User registered successfully";
    }
   @Override
public LoginResponse loginUser(LoginRequest request) {

    User user = userRepository
            .findByEmail(request.getEmail())
            .orElse(null);

    if (user == null) {
        throw new RuntimeException("User not found");
    }

    boolean matches = passwordEncoder.matches(
            request.getPassword(),
            user.getPassword());

    if (!matches) {
        throw new RuntimeException("Invalid password");
    }

    String token = jwtService.generateToken(user.getEmail());

    return new LoginResponse(token);
}
}