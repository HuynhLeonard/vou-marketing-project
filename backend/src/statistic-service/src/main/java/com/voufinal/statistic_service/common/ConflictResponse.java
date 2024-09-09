package com.voufinal.statistic_service.common;

import com.voufinal.statistic_service.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ConflictResponse extends ErrorResponse {
    public ConflictResponse() {
        super("Conflict", HttpStatus.CONFLICT, null);
    }

    public ConflictResponse(String message) {
        super(message, HttpStatus.CONFLICT, null);
    }
}