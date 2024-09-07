package com.voufinal.authservice.common;
import org.springframework.http.HttpStatus;

public class AuthFailureResponse extends ErrorResponse {
    public AuthFailureResponse() {
        super("Unauthorized", HttpStatus.UNAUTHORIZED, null);
    }

    public AuthFailureResponse(String message) {
        super(message, HttpStatus.UNAUTHORIZED, null);
    }
}