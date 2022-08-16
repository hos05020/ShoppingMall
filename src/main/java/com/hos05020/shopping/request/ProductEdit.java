package com.hos05020.shopping.request;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductEdit {


    @NotBlank(message = "수정할 상품명을 입력해주세요.")
    private String title;

    @Range(min = 1000,max = 1000000)
    private int price;

    @NotBlank(message = "수정할 내용을 입력해주세요.")
    private String content;

    private String imguuid;

    private String imgName;

    private String imgpath;


}
