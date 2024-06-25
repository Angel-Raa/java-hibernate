package com.github.angel.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
