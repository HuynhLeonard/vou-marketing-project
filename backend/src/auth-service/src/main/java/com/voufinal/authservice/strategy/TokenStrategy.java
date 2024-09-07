package com.voufinal.authservice.strategy;

import com.voufinal.authservice.model.User;

public interface TokenStrategy {
    String generateToken(User user);
    boolean validateToken(String token);
}
