package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.RequestResponse;
import com.example.ecommerce.response.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public GetRequestResponse<Category> listCategory(){
        return new GetRequestResponse<>(Status.OK, categoryRepository.findAll());
    }

    public ResponseEntity<RequestResponse> categoryCreate(Category category){
        category.setCategoryName(category.getCategoryName().toLowerCase());

        Category cat = categoryRepository.getByCategoryName(category.getCategoryName());
        if (Objects.nonNull(cat)){
            return new ResponseEntity<>(new RequestResponse(category.getCategoryName()+ " is already exist!", false), HttpStatus.BAD_REQUEST);
        }
//        categoryRepository.save(category);
        System.out.println("Success : "+ category.getCategoryName());
        categoryRepository.save(category);

        return new ResponseEntity<>(new RequestResponse("category is created", true), HttpStatus.CREATED);
    }
}
