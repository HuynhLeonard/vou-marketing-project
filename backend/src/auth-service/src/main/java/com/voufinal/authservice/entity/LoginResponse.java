package com.voufinal.authservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private Object account;

    // Constructors, Getters and Setters
    public LoginResponse(String token, Object account) {
        this.token = token;
        this.account = account;
    }

    public LoginResponse() {
    }


}
