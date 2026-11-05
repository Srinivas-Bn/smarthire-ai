package com.smarthire.service;

import com.smarthire.dto.LoginRequest;
import com.smarthire.dto.LoginResponse;
import com.smarthire.dto.RegisterRequest;


public interface UserService {

    String registerUser(RegisterRequest request);
    LoginResponse loginUser(LoginRequest request);
}