package com.example.ecommerce.controller;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.RequestResponse;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public GetRequestResponse<Category> listCategory(){
        return categoryService.listCategory();
    }

    @PostMapping("/create")
    public ResponseEntity<RequestResponse> categoryCreate(@RequestBody Category category){
        return categoryService.categoryCreate(category);
    }
}
