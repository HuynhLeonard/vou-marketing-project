package com.voufinal.event_service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.voufinal.event_service.common.ApiResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements ApiResponse {
    private boolean success = false;
    private int code;
    private String message;
    private Object errors;

    public ErrorResponse() {}

    public ErrorResponse(String message, HttpStatus status, Object errors) {
        this.message = message;
        this.code = status.value();
        this.errors = errors;
    }

}
