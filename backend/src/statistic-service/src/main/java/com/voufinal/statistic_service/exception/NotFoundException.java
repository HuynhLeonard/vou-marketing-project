package com.voufinal.statistic_service.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {}

    public NotFoundException(String message) {
        super(message);
    }
}
