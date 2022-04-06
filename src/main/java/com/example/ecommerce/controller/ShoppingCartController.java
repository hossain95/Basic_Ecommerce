package com.example.ecommerce.controller;

import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/list")
    public GetRequestResponse<ShoppingCart> shoppingCartList(){
        return shoppingCartService.shoppingCartList();
    }

    @GetMapping("/customer/{customerId}")
    public ShoppingCart shoppingCartByCustomer(@RequestBody @PathVariable("customerId") Long customerId){
        return shoppingCartService.shoppingCartByCustomer(customerId);
    }
}
