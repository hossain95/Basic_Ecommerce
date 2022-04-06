package com.example.ecommerce.service;

import com.example.ecommerce.converter.ProductConverter;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.RequestResponse;
import com.example.ecommerce.response.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public GetRequestResponse<Product> productList(){
        List<Product> products = productRepository.findAll();

        return new GetRequestResponse<>(Status.OK, products);
    }

    public ResponseEntity<RequestResponse> productCreate(ProductDto productDto){
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        if(category.isEmpty()){
            return new ResponseEntity<>(new RequestResponse("category does not found!", false), HttpStatus.BAD_REQUEST);
        }
        Product product = new ProductConverter().productDtoToProduct(productDto, category.get());

        try{
            productRepository.save(product);
            return new ResponseEntity<>(new RequestResponse("product is created", true), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new RequestResponse("product does not created", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
