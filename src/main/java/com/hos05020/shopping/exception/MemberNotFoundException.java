package com.hos05020.shopping.exception;

public class MemberNotFoundException extends ShoppingException{

    private static final String MESSAGE = "존재하지 않는 멤버입니다.";


    public MemberNotFoundException() {
        super(MESSAGE);
        addValidation("member","없는 멤버입니다.");
    }

    @Override
    public int geterrorCode() {
        return 404;
    }
}
