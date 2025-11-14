package com.yangzhie.arima.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yangzhie.arima.dto.LoginRequest;
import com.yangzhie.arima.dto.RegisterRequest;
import com.yangzhie.arima.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // Register post mapping
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String message = authService.register(request.getUsername(), request.getPassword(), request.getEmail());
        return ResponseEntity.ok(message);
    }

    // Login post mapping
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
