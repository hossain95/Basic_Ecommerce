package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.RequestResponse;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public GetRequestResponse<Product> productsList(){
        return productService.productList();
    }

    @PostMapping("/create")
    public ResponseEntity<RequestResponse> productCreate(@RequestBody ProductDto productDto){
        return productService.productCreate(productDto);
    }
}
