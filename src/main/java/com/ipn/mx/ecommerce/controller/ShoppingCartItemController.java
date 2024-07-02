package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.ShoppingCartItem;
import com.ipn.mx.ecommerce.service.interfaces.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-cart-items")
public class ShoppingCartItemController {

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @PostMapping
    public ResponseEntity<ShoppingCartItem> addItemToCart(@RequestBody ShoppingCartItem item) {
        ShoppingCartItem savedItem = shoppingCartItemService.save(item);
        return ResponseEntity.ok(savedItem);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Integer itemId) {
        shoppingCartItemService.deleteById(itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<ShoppingCartItem>> getItemsByCartId(@PathVariable Integer cartId) {
        List<ShoppingCartItem> items = shoppingCartItemService.findAllByShoppingCartId(cartId);
        return ResponseEntity.ok(items);
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartItem>> getAllItems() {
        List<ShoppingCartItem> items = shoppingCartItemService.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ShoppingCartItem> getItemById(@PathVariable Integer itemId) {
        Optional<ShoppingCartItem> item = shoppingCartItemService.findById(itemId);
        return item.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ShoppingCartItem> updateItemQuantity(@PathVariable Integer itemId, @RequestParam Integer qty) {
        ShoppingCartItem updatedItem = shoppingCartItemService.updateItemQuantity(itemId, qty);
        return ResponseEntity.ok(updatedItem);
    }
}
