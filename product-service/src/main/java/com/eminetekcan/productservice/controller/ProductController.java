package com.eminetekcan.productservice.controller;

import com.eminetekcan.productservice.dto.ProductRequest;
import com.eminetekcan.productservice.dto.ProductResponse;
import com.eminetekcan.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
         productService.createProduct(productRequest);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.gelAllProduct());
    }

    @DeleteMapping("/{id}")
    public void deletePoductById(@PathVariable String id){
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest){
       return new ResponseEntity<>( productService.updateProduct(id,productRequest),HttpStatus.ACCEPTED);
    }

}
