package com.example.exception;

public class RequirementsNotMetException extends RuntimeException{
    
    public RequirementsNotMetException(String message) {
        super(message);
    }
}
