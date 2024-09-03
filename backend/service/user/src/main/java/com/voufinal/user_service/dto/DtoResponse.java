package com.voufinal.user_service.dto;

import org.springframework.http.HttpStatus;

public class DtoResponse {
    private HttpStatus status;
    private String message;

    public DtoResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DtoResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
