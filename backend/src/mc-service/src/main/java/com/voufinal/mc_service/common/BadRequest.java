package com.voufinal.mc_service.common;

import com.voufinal.mc_service.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public class BadRequest extends ErrorResponse {
    public BadRequest() {
        super("Bad Request", HttpStatus.BAD_REQUEST, null);
    }

    public BadRequest(String message) {
        super(message, HttpStatus.BAD_REQUEST, null);
    }
}