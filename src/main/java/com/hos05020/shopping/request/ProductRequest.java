package com.hos05020.shopping.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductRequest {


    @NotBlank(message = "상품명을 입력해주세요.")
    private String title;

    @Range(min = 1000,max = 1000000, message = "가격은 1,000~1,000,000을 입력해야합니다.")
    private int price;  // 초기값 0

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private String imguuid;

    private String imgName;

    private String imgpath;

}
