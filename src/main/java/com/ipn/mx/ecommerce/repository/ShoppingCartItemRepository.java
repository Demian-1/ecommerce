package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {
    List<ShoppingCartItem> findAllByShoppingCartId(Integer cartId);
}
