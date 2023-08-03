package com.example.englishwordset.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectCustomException.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(ProjectCustomException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getCode()),
                HttpStatus.valueOf(e.getErrorCode().getCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgsExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(message, 400), HttpStatus.BAD_REQUEST);
    }
}
