package com.voufinal.event_service.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse implements ApiResponse{
    private boolean success = true;
    private String message;
    private int code;
    private Object metadata;

    public SuccessResponse() {}

    public SuccessResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public SuccessResponse(String message, HttpStatus status, Object metadata) {
        this.message = message;
        this.code = status.value();
        this.metadata = metadata;
    }
}
