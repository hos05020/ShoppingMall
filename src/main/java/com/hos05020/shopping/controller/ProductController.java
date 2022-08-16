package com.hos05020.shopping.controller;

import com.hos05020.shopping.request.ProductEdit;
import com.hos05020.shopping.request.ProductRequest;
import com.hos05020.shopping.response.PageResponse;
import com.hos05020.shopping.response.ProductResponse;
import com.hos05020.shopping.request.RequestPageDTO;
import com.hos05020.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public void register(@RequestBody @Valid ProductRequest request){
        productService.register(request);
        System.out.println("Save Product");
        System.out.println(request.getImguuid());
        System.out.println(request.getImgpath());
        System.out.println(request.getImgName());
    }

    @GetMapping("/products/{productId}")
    public ProductResponse get(@PathVariable Long productId){
        ProductResponse response = productService.get(productId);
        System.out.println("Get Product");
        return response;
    }

    @GetMapping("/products")
    public PageResponse getList(@ModelAttribute RequestPageDTO requestDTO){
        System.out.println("Get List Product");
        return productService.getList(requestDTO);
    }

    @PatchMapping("/products/{productId}")
    public void modify(@PathVariable Long productId ,@RequestBody @Valid ProductEdit edit){
        System.out.println("edit Product");
        productService.modify(productId,edit);

    }

    @DeleteMapping("/products/{productId}")
    public void delete(@PathVariable Long productId){
        System.out.println("Delete Product");
        productService.delete(productId);
    }

}
