package com.ipn.mx.ecommerce.service.interfaces;

import java.util.Optional;

import com.ipn.mx.ecommerce.model.ShoppingCart;

public interface ShoppingCartService {
    Optional<ShoppingCart> getCartByUserId(Integer userId);
    ShoppingCart createOrUpdateCart(ShoppingCart cart);
    void deleteCart(Integer cartId);
}
