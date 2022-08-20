package com.hos05020.shopping.repository;

import com.hos05020.shopping.domain.Product;
import com.hos05020.shopping.request.RequestPageDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
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
                .where(getSearch(requestDTO))
                .orderBy(product.id.desc())
                .offset(requestDTO.getOffset())
                .limit(requestDTO.getSize())
                .fetch();
    }

    @Override
    public Long getTotalPage(RequestPageDTO requestDTO) {
        return jpaQueryFactory.select(product.count())
                .where(getSearch(requestDTO))
                .from(product)
                .fetchOne();

    }


    public BooleanBuilder getSearch(RequestPageDTO requestPageDTO){

        String type = requestPageDTO.getType();
        String keyword = requestPageDTO.getKeyword();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        booleanBuilder.and(product.id.gt(0L));//이거 없으면 결과값이 없음

        if(type==null||type.trim().length()==0){
            return booleanBuilder;
        }

//        BooleanBuilder conditionBuilder = new BooleanBuilder();
//
//        if(type.contains("t")){
//            conditionBuilder.or(product.title.contains(keyword));
//        }
//        if(type.contains("c")){
//            conditionBuilder.or(product.content.contains(keyword));
//        }
//
//        booleanBuilder.and(conditionBuilder);
        if(type.contains("tc")){
            booleanBuilder.or(product.title.contains(keyword)).or(product.content.contains(keyword));
        }
        else if(type.contains("t")){
            booleanBuilder.and(product.title.contains(keyword));
        }
        else if(type.contains("c")){
            booleanBuilder.and(product.content.contains(keyword));
        }

        return booleanBuilder;
    }
}
