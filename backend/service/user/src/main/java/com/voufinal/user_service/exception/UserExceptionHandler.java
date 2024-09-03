package com.voufinal.user_service.exception;

import com.voufinal.user_service.dto.DtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        DtoResponse dtoResponse = new DtoResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoResponse);
    }

    @ExceptionHandler(BadReqException.class)
    public ResponseEntity<DtoResponse> handleBadReqException(BadReqException ex, WebRequest request) {
        DtoResponse dtoResponse = new DtoResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dtoResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DtoResponse> handleGlobalException(Exception ex, WebRequest request) {
        DtoResponse errorDto = new DtoResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}
