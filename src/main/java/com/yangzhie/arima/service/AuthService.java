package com.yangzhie.arima.service;

public interface AuthService {
    String register(String username, String password, String email);
    String login(String email, String password);
}
