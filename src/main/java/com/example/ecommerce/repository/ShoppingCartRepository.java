package com.example.ecommerce.repository;

import com.example.ecommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findShoppingCartByCustomer_Id(Long id);

}
