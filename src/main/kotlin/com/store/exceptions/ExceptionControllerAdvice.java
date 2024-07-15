package com.store.exceptions;

import com.store.model.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(InvalidProductTypeException.class)
    public ResponseEntity<ErrorResponseBody> exceptionHandler(InvalidProductTypeException e) {
        ErrorResponseBody errorResponse = new ErrorResponseBody();
        errorResponse.setError(e.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimestamp(String.valueOf(LocalDateTime.now()));
        errorResponse.setPath(ServletUriComponentsBuilder.fromCurrentServletMapping().toUriString());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}