package com.voufinal.user_service.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException{

    private ErrorMess error;

    public ApplicationException(ErrorMess errorMess) {
        super(errorMess.getMessage());
        this.error = errorMess;
    }
}
