package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.ShoppingCartItem;
import com.ipn.mx.ecommerce.repository.ShoppingCartItemRepository;
import com.ipn.mx.ecommerce.service.interfaces.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public ShoppingCartItem save(ShoppingCartItem item) {
        return shoppingCartItemRepository.save(item);
    }

    @Override
    public void deleteById(Integer itemId) {
        shoppingCartItemRepository.deleteById(itemId);
    }

    @Override
    public List<ShoppingCartItem> findAll() {
        return shoppingCartItemRepository.findAll();
    }

    @Override
    public Optional<ShoppingCartItem> findById(Integer itemId) {
        return shoppingCartItemRepository.findById(itemId);
    }

    @Override
    public List<ShoppingCartItem> findAllByShoppingCartId(Integer cartId) {
        return shoppingCartItemRepository.findAllByShoppingCartId(cartId);
    }

    @Override
    public ShoppingCartItem updateItemQuantity(Integer itemId, Integer qty) {
        ShoppingCartItem item = shoppingCartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));
        item.setQty(qty);
        return shoppingCartItemRepository.save(item);
    }
}
