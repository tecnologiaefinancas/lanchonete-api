package com.lanchonete.tech.adapters.api.controllers;


import com.lanchonete.tech.core.domain.exceptions.DomainException;
import com.lanchonete.tech.core.domain.exceptions.NotFoundException;
import com.lanchonete.tech.core.domain.validation.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException e) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(e));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(e));
    }

    public record ApiError(String message, List<Error> errors) {
        static ApiError from(DomainException ex) {
            return new ApiError(ex.getMessage(), ex.getErrors());
        }
    }
}
