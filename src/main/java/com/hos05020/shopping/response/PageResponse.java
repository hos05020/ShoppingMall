package com.hos05020.shopping.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse {

    private int page;
    private int size;
    private int totalPage;
    private List<ProductResponse> responses;




}
