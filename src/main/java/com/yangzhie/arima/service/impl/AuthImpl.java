package com.yangzhie.arima.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yangzhie.arima.model.User;
import com.yangzhie.arima.repository.UserRepository;
import com.yangzhie.arima.service.AuthService;
import com.yangzhie.arima.util.JwtUtil;

@Service
public class AuthImpl implements AuthService {
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Register the user
    public String register(String username, String password, String email) {
        if (repo.findByEmail(email).isPresent()) {
            // Alt route: email exists
            throw new RuntimeException("Email exists already.");
        } else {
            // Main route: register
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);

            repo.save(user);

            return "User has been successfully registered.";
        }
    }

    // Login user
    public String login(String email, String password) {
        // Case: no registered email found
        User user = repo.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found."));
        
        // Case: passwords don't match
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Username or password is incorrect.");
        } else {
            return jwtUtil.generateToken(user.getEmail());
        }
    }
}
