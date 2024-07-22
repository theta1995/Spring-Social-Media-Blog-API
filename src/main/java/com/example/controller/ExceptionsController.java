package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.example.exception.RequirementsNotMetException;
import com.example.exception.UsernameAlreadyExistsException;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(RequirementsNotMetException.class)
    public ResponseEntity<String> handleRequirementsNotMetException(RequirementsNotMetException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
