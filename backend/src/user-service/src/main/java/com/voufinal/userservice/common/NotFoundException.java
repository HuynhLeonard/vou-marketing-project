package com.voufinal.userservice.common;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
