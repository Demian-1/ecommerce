package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.ShoppingCart;
import com.ipn.mx.ecommerce.repository.ShoppingCartRepository;
import com.ipn.mx.ecommerce.service.interfaces.ShoppingCartService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart save(ShoppingCart sc) {
        return shoppingCartRepository.save(sc);
    }

    public void emptyCart(int userId) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            ShoppingCart cart = optionalCart.get();
            cart.getItems().clear();
            shoppingCartRepository.save(cart);
        }
    }

    @Override
    public Optional<ShoppingCart> getCartByUserId(Integer userId) {
        return shoppingCartRepository.findByUserId(userId);
    }

    @Override
    public ShoppingCart createOrUpdateCart(ShoppingCart cart) {
        Optional<ShoppingCart> existingCartOpt = shoppingCartRepository.findByUserId(cart.getUser().getId());
        if (existingCartOpt.isPresent()) {
            ShoppingCart existingCart = existingCartOpt.get();
            // Aquí puedes actualizar los campos necesarios del carrito existente
            existingCart.setItems(cart.getItems());
            return shoppingCartRepository.save(existingCart);
        } else {
            return shoppingCartRepository.save(cart);
        }
    }

    @Override
    public void deleteCart(Integer cartId) {
        shoppingCartRepository.deleteById(cartId);
    }
}
