package com.example.ecommerce.service;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.repository.ShoppingCartRepository;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCardRepository;

    public GetRequestResponse<ShoppingCart> shoppingCartList(){
        return new GetRequestResponse<>(Status.OK, shoppingCardRepository.findAll());
    }

    public ShoppingCart createShoppingCart(Customer customer){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(customer);
        return shoppingCardRepository.save(shoppingCart);
    }

    public ShoppingCart shoppingCartByCustomer(Long customerId){
        ShoppingCart shoppingCart = shoppingCardRepository.findShoppingCartByCustomer_Id(customerId);
        return shoppingCart;
    }
}
