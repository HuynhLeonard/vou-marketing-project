package com.voufinal.event_service.common;

import com.voufinal.event_service.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public class UnprocessableContentResponse extends ErrorResponse {
    public UnprocessableContentResponse(Object errors) {
        super("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }

    public UnprocessableContentResponse(String message, Object errors) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }
}
