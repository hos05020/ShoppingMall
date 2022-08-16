package com.hos05020.shopping.service;

import com.hos05020.shopping.domain.Product;
import com.hos05020.shopping.exception.ProductNotFound;
import com.hos05020.shopping.repository.ProductRepository;
import com.hos05020.shopping.request.ProductEdit;
import com.hos05020.shopping.request.ProductRequest;
import com.hos05020.shopping.response.PageResponse;
import com.hos05020.shopping.response.ProductResponse;
import com.hos05020.shopping.request.RequestPageDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;


@SpringBootTest
@Transactional
class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository repository;


    @Test
    @DisplayName("등록 테스트")
    void test(){
        ProductRequest request = ProductRequest.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .price(10000)
                .build();

        Long id = productService.register(request);


        Product product = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("없는 상품입니다."));

        Assertions.assertThat(product.getId()).isEqualTo(id);
        Assertions.assertThat(product.getTitle()).isEqualTo(request.getTitle());
        Assertions.assertThat(product.getContent()).isEqualTo(request.getContent());

    }

   @Test
   @DisplayName("등록 상품 찾기")
   void test2(){
       ProductRequest request = ProductRequest.builder()
               .title("제목입니다.")
               .content("내용입니다.")
               .price(10000)
               .build();

       Long id = productService.register(request);

       ProductResponse response = productService.get(id);

       Assertions.assertThat(response.getTitle()).isEqualTo(request.getTitle());
       Assertions.assertThat(response.getContent()).isEqualTo(request.getContent());
       Assertions.assertThat(response.getPrice()).isEqualTo(request.getPrice());
   }

    @Test
    @DisplayName("페이지 상품 목록")
    void test3(){
        IntStream.rangeClosed(0,30).forEach(i->{
            Product product = Product.builder()
                    .title("제목"+i)
                    .content("내용"+i)
                    .price(i*100+5000)
                    .build();
            repository.save(product);
        });

        PageResponse response = productService.getList(RequestPageDTO.builder().page(0).size(5).build());


        Assertions.assertThat(response.getPage()).isEqualTo(0);
        Assertions.assertThat(response.getSize()).isEqualTo(5);
        Assertions.assertThat(response.getResponses().get(0).getPrice()).isEqualTo(8000);
        Assertions.assertThat(response.getTotalPage()).isEqualTo(7);
    }


    @Test
    @DisplayName("상품 수정")
    void test4(){
        Product product = Product.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .price(10000)
                .build();
        repository.save(product);

        ProductEdit edit = ProductEdit.builder()
                        .title("제목수정했습니다.")
                                .content("내용수정했습니다.")
                .price(30000)
                                        .build();

        productService.modify(product.getId(), edit);

        Product findProduct = repository.findById(product.getId()).orElseThrow(() -> new IllegalArgumentException("없는 상품입니다."));

        Assertions.assertThat(findProduct.getTitle()).isEqualTo("제목수정했습니다.");
        Assertions.assertThat(findProduct.getContent()).isEqualTo("내용수정했습니다.");
        Assertions.assertThat(findProduct.getPrice()).isEqualTo(30000);
    }


    @Test
    @DisplayName("상품 삭제")
    void test5(){
        Product product = Product.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .price(10000)
                .build();
        repository.save(product);

        Long id = product.getId();

        productService.delete(product.getId());

       Assertions.assertThatThrownBy(()->{
           productService.get(id);
       }).isInstanceOf(ProductNotFound.class);
    }





}