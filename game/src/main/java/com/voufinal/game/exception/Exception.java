package com.voufinal.game.exception;

import com.voufinal.game.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// handler error of not found
@ControllerAdvice
public class Exception extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ResponseDto> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    // handle for 505
//    @ExceptionHandler(Exception.class)
//    ResponseEntity<ResponseDto> handleGlobalException(Exception ex, WebRequest request) {
//        ResponseDto err = new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
//    }
}
