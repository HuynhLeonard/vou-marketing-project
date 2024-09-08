package com.voufinal.gift_service.common;

import com.voufinal.gift_service.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public class NotFoundResponse extends ErrorResponse {
    public NotFoundResponse() {
        super("Not Found", HttpStatus.NOT_FOUND, null);
    }

    public NotFoundResponse(String message) {
        super(message, HttpStatus.NOT_FOUND, null);
    }
}
