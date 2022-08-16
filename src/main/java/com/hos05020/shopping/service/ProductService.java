package com.hos05020.shopping.service;

import com.hos05020.shopping.domain.Product;
import com.hos05020.shopping.exception.ProductNotFound;
import com.hos05020.shopping.repository.ProductRepository;
import com.hos05020.shopping.request.ProductEdit;
import com.hos05020.shopping.request.ProductRequest;
import com.hos05020.shopping.response.PageResponse;
import com.hos05020.shopping.response.ProductResponse;
import com.hos05020.shopping.request.RequestPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository repository;

    @Transactional
    public Long register(ProductRequest request){

        Product product = Product.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .content(request.getContent())
                .imguuid(request.getImguuid())
                .imgName(request.getImgName())
                .imgpath(request.getImgpath())
                .build();
        repository.save(product);

        return product.getId();
    }


    public ProductResponse get(Long productId) {

        Product findProduct = repository.findById(productId).orElseThrow(() -> new ProductNotFound());
        ProductResponse response = new ProductResponse(findProduct);
        return response;

    }

    public PageResponse getList(RequestPageDTO requestDTO) {
        List<ProductResponse> productResponses = repository.getList(requestDTO).stream()
                .map(product -> new ProductResponse(product)).collect(Collectors.toList());

        int totalCount = repository.getTotalPage(requestDTO).intValue();
        double totalPage = Math.ceil((double) totalCount / requestDTO.getSize());

        PageResponse pageResponse = PageResponse.builder()
                .page(requestDTO.getPage())
                .size(requestDTO.getSize())
                .totalPage((int)totalPage)
                .responses(productResponses)
                .build();

        return pageResponse;

    }

    @Transactional
    public void modify(Long productId, ProductEdit edit) {
        Product findProduct = repository.findById(productId).orElseThrow(() -> new ProductNotFound());
        findProduct.modify(edit);

    }

    @Transactional
    public void delete(Long productId) {
        Product findProduct = repository.findById(productId).orElseThrow(() -> new ProductNotFound());
        repository.delete(findProduct);
    }


}
