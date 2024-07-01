package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.ShoppingCart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findByUserId(Integer userId);
}