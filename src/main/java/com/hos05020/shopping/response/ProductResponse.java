package com.hos05020.shopping.response;

import com.hos05020.shopping.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String title;
    private int price;
    private String content;

    private String imguuid;

    private String imgName;

    private String imgpath;

    private String imgsrc;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public ProductResponse(Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
        content = product.getContent();

        imguuid = product.getImguuid();
        imgName = product.getImgName();
        imgpath = product.getImgpath();

        regDate = product.getRegDate();
        modDate = product.getModDate();
        imgsrc = getImageURL(product.getImgpath(),product.getImguuid(),product.getImgName());
    }

    public String getImageURL(String folderPath, String uuid,String fileName){
        try {
            return URLEncoder.encode(folderPath+File.separator+uuid+"_"+fileName,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
}
