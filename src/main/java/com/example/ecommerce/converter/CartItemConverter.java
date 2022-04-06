package com.example.ecommerce.converter;

import com.example.ecommerce.dto.CartItemDto;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ShoppingCart;

public class CartItemConverter {

    public CartItem cartItemDtoToCartItem(CartItemDto cartItemDto, Product product){
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setProduct(product);
//        cartItem.setShoppingCart(shoppingCart);
        cartItem.setSubTotal(product.getPrice()*cartItemDto.getQuantity());
        cartItem.setSubTotalDeliveryCharge(product.getDeliveryCharge()*cartItemDto.getQuantity());
        return cartItem;
    }
}
