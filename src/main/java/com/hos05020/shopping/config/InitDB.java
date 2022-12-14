package com.hos05020.shopping.config;

import com.hos05020.shopping.domain.Member;
import com.hos05020.shopping.domain.Product;
import com.hos05020.shopping.repository.MemberRepository;
import com.hos05020.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.stream.IntStream;

@Component
@Profile("local")
@RequiredArgsConstructor
public class InitDB {

    private final StartService service;

    @PostConstruct
    public void init(){
         service.initdata1();
    }

    @Component
    @Profile("local")
    @Transactional
    @RequiredArgsConstructor
    static class StartService{

        private final ProductRepository repository;
        private final MemberRepository memberRepository;

        public void initdata1(){



            Member member1 = Member.builder()
                    .loginId("test")
                    .password("test!")
                    .nickname("테스터")
                    .build();

            Member member2 = Member.builder()
                    .loginId("hos05020")
                    .password("hos1179727!")
                    .nickname("hajangwon")
                    .build();
            memberRepository.save(member1);
            memberRepository.save(member2);



            IntStream.rangeClosed(1,250).forEach(i->{
                Product product = Product.builder()
                        .title("제목"+i)
                        .content("내용"+i)
                        .price(1000+(i*100))
                        .imgName("극한직첩.jpg")
                        .imgpath("2022"+ File.separator+"08"+File.separator+"15")
                        .imguuid("93c264d9-04ee-4e25-ab0e-78a0737e8bbb")
                        .build();

                if(i%2==0){
                    product.setMember(Member.builder().id(member1.getId()).build());
                }else {
                    product.setMember(Member.builder().id(member2.getId()).build());
                }

                repository.save(product);
            });
        }


        public String getImageURL(String folderPath, String uuid,String fileName){
            try {
                return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            return "";
        }

    }

}
