package com.voufinal.mc_service.common;

import com.voufinal.mc_service.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ForbiddenResponse extends ErrorResponse {
    public ForbiddenResponse() {
        super("Forbidden", HttpStatus.FORBIDDEN, null);
    }

    public ForbiddenResponse(String message) {
        super(message, HttpStatus.FORBIDDEN, null);
    }
}
