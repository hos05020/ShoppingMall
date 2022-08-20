package com.hos05020.shopping.domain;

import com.hos05020.shopping.request.ProductEdit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String title;

    private int price;

    @Lob
    private String content;

    private String imguuid;

    private String imgName;

    private String imgpath;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public void modify(ProductEdit edit){
        this.title = edit.getTitle();
        this.price = edit.getPrice();
        this.content = edit.getContent();
        this.imguuid = edit.getImguuid();
        this.imgName = edit.getImgName();
        this.imgpath = edit.getImgpath();
    }

    public void setMember(Member member){
        this.member = member;
    }

}
