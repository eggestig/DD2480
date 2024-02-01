package com.mycompany.app;

public class InvalidParameterException extends RuntimeException{

    public InvalidParameterException(String message) {
        super(message);
    }
}