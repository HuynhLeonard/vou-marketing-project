package com.voufinal.gameservice.common;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {}

    public NotFoundException(String message) {
        super(message);
    }
}