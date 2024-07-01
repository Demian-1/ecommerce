package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.Product;
import com.ipn.mx.ecommerce.model.ShoppingCart;
import com.ipn.mx.ecommerce.model.ShoppingCartItem;
import com.ipn.mx.ecommerce.model.SiteUser;
import com.ipn.mx.ecommerce.service.interfaces.ProductService;
import com.ipn.mx.ecommerce.service.interfaces.ShoppingCartService;
import com.ipn.mx.ecommerce.service.interfaces.ShoppingCartItemService;
import com.ipn.mx.ecommerce.service.interfaces.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SiteUserService userService;

    @PostMapping("/user/{userId}/product/{productId}/qty/{qty}")
    public ResponseEntity<ShoppingCartItem> addProductToShoppingCart(@PathVariable int productId, @PathVariable int userId, @PathVariable int qty){
        Optional<ShoppingCart> cart = shoppingCartService.getCartByUserId(userId);
        if(cart.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Optional<Product> p = productService.getById(productId);
        if(p.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ShoppingCartItem item = new ShoppingCartItem();
        item.setProduct(p.get());
        item.setQty(qty);
        item.setShoppingCart(cart.get());
        shoppingCartItemService.addItemToCart(item);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ShoppingCart> getCartByUserId(@PathVariable int userId) {
        Optional<SiteUser> user = userService.getUserById(userId);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ShoppingCart sc = new ShoppingCart();
        sc.setUser(user.get());

        ShoppingCart savedSC = shoppingCartService.save(sc);
        return ResponseEntity.ok(savedSC);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Optional<ShoppingCart>> getCartByUserId(@PathVariable Integer userId) {
        Optional<ShoppingCart> cart = shoppingCartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> createOrUpdateCart(@RequestBody ShoppingCart cart) {
        ShoppingCart updatedCart = shoppingCartService.createOrUpdateCart(cart);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer cartId) {
        shoppingCartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<ShoppingCartItem>> getItemsByCartId(@PathVariable Integer cartId) {
        List<ShoppingCartItem> items = shoppingCartItemService.getItemsByCartId(cartId);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<ShoppingCartItem> addItemToCart(@PathVariable Integer cartId, @RequestBody ShoppingCartItem item) {
        item.setShoppingCart(new ShoppingCart(cartId, null, null));
        ShoppingCartItem addedItem = shoppingCartItemService.addItemToCart(item);
        return ResponseEntity.ok(addedItem);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ShoppingCartItem> updateItemQuantity(@PathVariable Integer itemId, @RequestParam Integer qty) {
        ShoppingCartItem updatedItem = shoppingCartItemService.updateItemQuantity(itemId, qty);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Integer itemId) {
        shoppingCartItemService.removeItemFromCart(itemId);
        return ResponseEntity.noContent().build();
    }
}
