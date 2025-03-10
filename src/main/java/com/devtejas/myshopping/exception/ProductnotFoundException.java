package com.devtejas.myshopping.exception;

public class ProductnotFoundException extends RuntimeException {
    public ProductnotFoundException(String message){
        super(message);
    }
}
