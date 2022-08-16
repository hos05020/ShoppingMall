package com.hos05020.shopping.controller;

import com.hos05020.shopping.exception.ShoppingException;
import com.hos05020.shopping.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {



        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
        public ErrorResponse ValidException(MethodArgumentNotValidException e){
            ErrorResponse response = ErrorResponse.builder()
                    .code("400")
                    .message("잘못된 입력입니다.")
                    .build();

            for (FieldError fieldError : e.getFieldErrors()) {
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                response.addValidation(fieldName,message);
            }
            return response;
        }

        @org.springframework.web.bind.annotation.ExceptionHandler(ShoppingException.class)
    public ResponseEntity<ErrorResponse> shoppingError (ShoppingException e){
            ErrorResponse response = ErrorResponse.builder()
                    .code(String.valueOf(e.geterrorCode()))
                    .message(e.getMessage())
                    .validation(e.getValidation())
                    .build();

            ResponseEntity<ErrorResponse> body = ResponseEntity.status(e.geterrorCode()).body(response);
            return body;


        }



}
