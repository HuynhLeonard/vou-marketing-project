package com.voufinal.user_service.exception;

public class BadReqException extends RuntimeException{
    public BadReqException(String message){
        super(message);
    }
}
