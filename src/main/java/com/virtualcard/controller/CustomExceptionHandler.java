package com.virtualcard.controller;


import com.virtualcard.exception.InvalidFundException;
import com.virtualcard.exception.InvalidStatusException;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<String> handleInvalidState(InvalidStatusException ex) {
        return getStringResponseEntity(ex);
    }

    @ExceptionHandler(InvalidFundException.class)
    public ResponseEntity<String> handleInvalidState(InvalidFundException ex) {
        return getStringResponseEntity(ex);
    }

    private static @NonNull ResponseEntity<String> getStringResponseEntity(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
