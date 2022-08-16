package com.hos05020.shopping.repository;

import com.hos05020.shopping.domain.Product;
import com.hos05020.shopping.request.RequestPageDTO;

import java.util.List;

public interface ProductRepositoryCustom {


    List<Product> getList(RequestPageDTO requestDTO);

    Long getTotalPage(RequestPageDTO requestPageDTO);

}
