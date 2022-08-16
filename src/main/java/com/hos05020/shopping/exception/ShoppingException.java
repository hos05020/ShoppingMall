package com.hos05020.shopping.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ShoppingException extends RuntimeException{


    private final Map<String,String> validation = new HashMap<>();


    public ShoppingException(String message) {
        super(message);
    }

    public ShoppingException(String message, Throwable cause) {
        super(message, cause);
    }


    public abstract int geterrorCode();

    public void addValidation(String fieldName,String message){
        validation.put(fieldName,message);
    }

}
