package com.example.ecommerce.converter;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductConverter {

    public Product productDtoToProduct(ProductDto productDto, Category category){

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        product.setDeliveryCharge(productDto.getDeliveryCharge());
        return product;
    }
}
