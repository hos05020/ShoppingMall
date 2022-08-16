package com.hos05020.shopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hos05020.shopping.domain.Product;
import com.hos05020.shopping.repository.ProductRepository;
import com.hos05020.shopping.request.ProductEdit;
import com.hos05020.shopping.request.ProductRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository repository;



    @Test
    @DisplayName("등록 시험")
    void test1() throws Exception {

        ProductRequest request = ProductRequest.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .price(10000)
                .build();


        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/products")
                .contentType(APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());



    }

    @Test
    @DisplayName("등록할 때 제목 혹은 가격은 공백이 될 수 없습니다.")
    void test2() throws Exception {

        ProductRequest request = ProductRequest.builder()
                .content("내용입니다.")
                .build();


        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 입력입니다."))
                .andExpect(jsonPath("$.validation.title").value("상품명을 입력해주세요."))
                .andExpect(jsonPath("$.validation.price").value("가격은 1,000~1,000,000을 입력해야합니다."))
                .andDo(MockMvcResultHandlers.print());



    }

    @Test
    @DisplayName("상품 찾기.")
    void test3() throws Exception {

        Product product = Product.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .price(10000)
                .build();


        repository.save(product);

        mockMvc.perform(get("/products/{productId}",product.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.title").value("제목입니다."))
                .andExpect(jsonPath("$.price").value(10000))
                .andExpect(jsonPath("$.content").value("내용입니다."))
                .andDo(MockMvcResultHandlers.print());



    }

    @Test
    @DisplayName("상품 목록 테스트.")
    void test4() throws Exception {
        IntStream.rangeClosed(0,30).forEach(i->{
            Product product = Product.builder()
                    .title("제목"+i)
                    .content("내용"+i)
                    .price(i*100+5000)
                    .build();
            repository.save(product);
        });


        mockMvc.perform(get("/products?page=0&size=5")
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.totalPage").value(7))
                .andExpect(jsonPath("$.responses[0].title").value("제목30"))
                .andExpect(jsonPath("$.responses[0].content").value("내용30"))
                .andExpect(jsonPath("$.responses[0].price").value(8000))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("상품 수정 테스트")
    void test5() throws Exception {
        Product product = Product.builder()
                .title("제목")
                .content("내용")
                .price(10000)
                .build();

        repository.save(product);

        ProductEdit edit = ProductEdit.builder()
                        .title("제목수정")
                                .content("내용수정")
                .price(5000)
                                        .build();
        String json = objectMapper.writeValueAsString(edit);

        mockMvc.perform(patch("/products/{productId}",product.getId())
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Product findProduct = repository.findById(product.getId()).orElseThrow(() -> new IllegalArgumentException("없는 상품입니다."));

        Assertions.assertThat(findProduct.getTitle()).isEqualTo("제목수정");
        Assertions.assertThat(findProduct.getContent()).isEqualTo("내용수정");
        Assertions.assertThat(findProduct.getPrice()).isEqualTo(5000);


    }


    @Test
    @DisplayName("상품 삭제 테스트")
    void test6() throws Exception {
        Product product = Product.builder()
                .title("제목")
                .content("내용")
                .price(10000)
                .build();

        repository.save(product);
        Long id = product.getId();


        mockMvc.perform(delete("/products/{productId}",product.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Assertions.assertThatThrownBy(()->{
            repository.findById(id).orElseThrow(()->new IllegalArgumentException("없는 상품입니다."));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("없는 상품 찾기")
    void test7() throws Exception {


        Product product = Product.builder()
                .title("제목")
                .content("내용")
                .price(10000)
                .build();

        repository.save(product);

        mockMvc.perform(delete("/products/{productId}",product.getId()+1L)
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("404"))
                .andExpect(jsonPath("$.message").value("존재하지 않는 상품입니다."))
                .andExpect(jsonPath("$.validation.id").value("없는 상품입니다."))
                .andDo(MockMvcResultHandlers.print());
    }

}