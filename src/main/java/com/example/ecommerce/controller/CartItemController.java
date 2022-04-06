package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartItemDto;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.RequestResponse;
import com.example.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-item")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/list")
    public GetRequestResponse<CartItem> cartItems(){
        return cartItemService.cartItems();
    }

    @PostMapping("/create")
    public ResponseEntity<RequestResponse> cartItemCreate(@RequestBody CartItemDto cartItemDto){
        return cartItemService.cartItemCreate(cartItemDto);
    }
}
