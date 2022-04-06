package com.example.ecommerce.service;

import com.example.ecommerce.converter.CartItemConverter;
import com.example.ecommerce.dto.CartItemDto;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ShoppingCartRepository;
import com.example.ecommerce.response.GetRequestResponse;
import com.example.ecommerce.response.RequestResponse;
import com.example.ecommerce.response.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;



    public GetRequestResponse<CartItem> cartItems(){
        return new GetRequestResponse<>(Status.OK, cartItemRepository.findAll());
    }

    public ResponseEntity<RequestResponse> cartItemCreate(CartItemDto cartItemDto){


        Optional<Product> product = productRepository.findById(cartItemDto.getProductId());

        if (product.isEmpty()){
            return new ResponseEntity<>(new RequestResponse("Product does not found!", false), HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> customer = customerRepository.findById(cartItemDto.getCustomerId());
        if (customer.isEmpty()){
            return new ResponseEntity<>(new RequestResponse("Customer does not found!", false), HttpStatus.BAD_REQUEST);
        }
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByCustomer_Id(cartItemDto.getCustomerId());
        if (Objects.isNull(shoppingCart)){

            shoppingCart = shoppingCartService.createShoppingCart(customer.get());
        }


        CartItem cartItem = new CartItemConverter().cartItemDtoToCartItem(cartItemDto, product.get());
        cartItem.setSubTotalDeliveryCharge(cartItem.getQuantity()*product.get().getDeliveryCharge());

        shoppingCart.setTotalProductPrice(shoppingCart.getTotalProductPrice()+cartItem.getSubTotal());
        shoppingCart.setTotalDeliveryCharge(shoppingCart.getTotalDeliveryCharge()+cartItem.getSubTotalDeliveryCharge());
        shoppingCart.setTotal(shoppingCart.getTotalProductPrice()+shoppingCart.getTotalDeliveryCharge());

        cartItem.setShoppingCart(shoppingCart);

        cartItemRepository.save(cartItem);

        return new ResponseEntity<>(new RequestResponse("item added", true), HttpStatus.CREATED);
    }
}
