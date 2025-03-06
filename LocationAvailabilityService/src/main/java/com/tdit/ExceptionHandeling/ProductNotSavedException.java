package com.tdit.ExceptionHandeling;

public class ProductNotSavedException extends RuntimeException {
    public ProductNotSavedException(String message) {
        super(message);
    }
}
