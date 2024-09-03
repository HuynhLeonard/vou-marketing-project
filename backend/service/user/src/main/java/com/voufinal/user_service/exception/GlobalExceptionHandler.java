package com.voufinal.user_service.exception;

import com.voufinal.user_service.dto.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        log.error("Exception: ", exception);
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorMess.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorMess.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = ApplicationException.class)
    ResponseEntity<ApiResponse> handlingAppException(ApplicationException exception) {
        ErrorMess errorMess = exception.getError();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorMess.getCode());
        apiResponse.setMessage(errorMess.getMessage());

        return ResponseEntity.status(errorMess.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorMess errorMess = ErrorMess.UNAUTHORIZED;

        return ResponseEntity.status(errorMess.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorMess.getCode())
                        .message(errorMess.getMessage())
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorMess errorMess = ErrorMess.INVALID_ROLE;
        Map<String, Object> attributes = null;
        try {
            errorMess = ErrorMess.valueOf(enumKey);

            var constraintViolation = exception.getBindingResult().getAllErrors().get(0);

            attributes = constraintViolation.unwrap(ConstraintViolation.class).getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());

        } catch (IllegalArgumentException e) {

        }

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorMess.getCode());
        apiResponse.setMessage(
                Objects.nonNull(attributes)
                        ? mapAttribute(errorMess.getMessage(), attributes)
                        : errorMess.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
