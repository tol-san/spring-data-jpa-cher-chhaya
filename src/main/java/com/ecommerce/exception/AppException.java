package com.ecommerce.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class AppException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceException(
            ResponseStatusException e
    ) {
        ErrorResponse<?> errorResponse = ErrorResponse.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .message("Service exception")
                .errors(e.getReason())
                .build();

        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<?> handleValidationException(MethodArgumentNotValidException exception) {

       log.error("Validation failed");

        List<FieldErrorResponse> fields = new ArrayList<>();
        exception.getFieldErrors()
                .forEach(fiedlError -> {
                    FieldErrorResponse field = FieldErrorResponse.builder()
                            .field(fiedlError.getField())
                            .message(fiedlError.getDefaultMessage())
                            .build();
                    fields.add(field);
                });
        return ErrorResponse.builder()
                .status(false)
                .code(400)
                .message("Validation Error")
                .errors(fields)
                .build();
    }
}
