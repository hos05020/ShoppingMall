package com.hos05020.shopping.domain;

import com.hos05020.shopping.request.ProductEdit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String title;

    private int price;

    @Lob
    private String content;

    private String imguuid;

    private String imgName;

    private String imgpath;


    public void modify(ProductEdit edit){
        this.title = edit.getTitle();
        this.price = edit.getPrice();
        this.content = edit.getContent();
        this.imguuid = edit.getImguuid();
        this.imgName = edit.getImgName();
        this.imgpath = edit.getImgpath();
    }


}
