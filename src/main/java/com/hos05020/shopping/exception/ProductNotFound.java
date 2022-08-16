package com.hos05020.shopping.exception;

public class ProductNotFound extends ShoppingException{

    private static final String MESSAGE = "존재하지 않는 상품입니다.";


    public ProductNotFound() {
        super(MESSAGE);
        addValidation("id","없는 상품입니다.");
    }

    @Override
    public int geterrorCode() {
        return 404;
    }
}
