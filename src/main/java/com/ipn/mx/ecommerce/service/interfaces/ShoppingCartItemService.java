package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartItemService {
    ShoppingCartItem addItemToCart(ShoppingCartItem item);
    void removeItemFromCart(Integer itemId);
    List<ShoppingCartItem> getItemsByCartId(Integer cartId);
    ShoppingCartItem updateItemQuantity(Integer itemId, Integer qty);
}
