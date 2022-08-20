package com.hos05020.shopping.exception;

public class ProductNotEqualMemberException extends ShoppingException{

    private static final String MESSAGE = "상품을 수정할 권한이 없습니다.";


    public ProductNotEqualMemberException() {
        super(MESSAGE);
        addValidation("member","상품을 수정할 권한이 없습니다.");
    }

    @Override
    public int geterrorCode() {
        return 401;
    }
}
