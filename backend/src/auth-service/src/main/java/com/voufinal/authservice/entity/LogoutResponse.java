package com.voufinal.authservice.entity;

public class LogoutResponse {
    private String message;

    public LogoutResponse(String message) {
        this.message = message;
    }

    public LogoutResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
