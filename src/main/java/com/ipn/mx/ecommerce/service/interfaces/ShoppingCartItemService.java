package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemService {
    ShoppingCartItem save(ShoppingCartItem item);
    void deleteById(Integer itemId);
    List<ShoppingCartItem> findAll();
    Optional<ShoppingCartItem> findById(Integer itemId);
    List<ShoppingCartItem> findAllByShoppingCartId(Integer cartId);
    ShoppingCartItem updateItemQuantity(Integer itemId, Integer qty);
}
