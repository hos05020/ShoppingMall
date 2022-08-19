package com.hos05020.shopping.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {


    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

}
