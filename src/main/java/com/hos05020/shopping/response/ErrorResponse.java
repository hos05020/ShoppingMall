package com.hos05020.shopping.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class ErrorResponse {
    private String code;
    private String message;
    private final Map<String, String> validation;


    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation!=null?validation:new HashMap<>();
    }

    public void addValidation(String fieldName,String message){
        this.validation.put(fieldName,message);
    }
}
