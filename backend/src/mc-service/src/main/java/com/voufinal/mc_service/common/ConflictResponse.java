package com.voufinal.mc_service.common;

import com.voufinal.mc_service.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ConflictResponse extends ErrorResponse {
    public ConflictResponse() {
        super("Conflict", HttpStatus.CONFLICT, null);
    }

    public ConflictResponse(String message) {
        super(message, HttpStatus.CONFLICT, null);
    }
}
