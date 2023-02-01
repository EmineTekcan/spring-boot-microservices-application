package com.eminetekcan.productservice.service;

import com.eminetekcan.productservice.dto.ProductRequest;
import com.eminetekcan.productservice.dto.ProductResponse;
import com.eminetekcan.productservice.model.Product;
import com.eminetekcan.productservice.repsitory.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product= Product.builder()
                        .name(productRequest.getName())
                        .price(productRequest.getPrice())
                        .description(productRequest.getDescription())
                        .build();
        productRepository.save(product);

    }

    public List<ProductResponse> gelAllProduct(){
        List<Product> products=productRepository.findAll();
       return products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse=ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        return productResponse;
    }

    public void deleteProductById(String id){
        productRepository.deleteById(id);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product product=productRepository.findById(id).orElse(null);
        if (product != null)
           return mapToProductResponse(product);
        else
            return null;
    }
}
