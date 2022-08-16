package com.hos05020.shopping.repository;

import com.hos05020.shopping.domain.Product;
import com.hos05020.shopping.request.RequestPageDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.hos05020.shopping.domain.QProduct.*;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{


    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> getList(RequestPageDTO requestDTO) {
        return jpaQueryFactory.selectFrom(product)
                .orderBy(product.id.desc())
                .offset(requestDTO.getOffset())
                .limit(requestDTO.getSize())
                .fetch();
    }

    @Override
    public Long getTotalPage(RequestPageDTO requestDTO) {
        return jpaQueryFactory.select(product.count())
                .from(product)
                .fetchOne();

    }
}
