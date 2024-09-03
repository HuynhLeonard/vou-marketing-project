package com.voufinal.user_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorMess {
    INVALID_ROLE(1000, "Invalid_Role error", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1001, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1002, "Unauthenticated error", HttpStatus.UNAUTHORIZED),
    UNCATEGORIZED_EXCEPTION(1003, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(1004, "Unauthorized error", HttpStatus.FORBIDDEN),
    ;
    ErrorMess(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
