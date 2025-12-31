package com.amer.microservice.exception;

public class BussinessException extends RuntimeException{
    public BussinessException(String message) {
        super(message);
    }
}
