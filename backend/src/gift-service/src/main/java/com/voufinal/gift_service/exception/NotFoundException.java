package com.voufinal.gift_service.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {}

    public NotFoundException(String message) {
        super(message);
    }
}
