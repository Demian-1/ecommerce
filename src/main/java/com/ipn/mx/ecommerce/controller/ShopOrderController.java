package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.ShopOrder;
import com.ipn.mx.ecommerce.service.interfaces.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop-orders")
public class ShopOrderController {

    @Autowired
    private ShopOrderService shopOrderService;

    @PostMapping
    public ResponseEntity<ShopOrder> createShopOrder(@RequestBody ShopOrder shopOrder) {
        return ResponseEntity.ok(shopOrderService.saveShopOrder(shopOrder));
    }

    @GetMapping
    public ResponseEntity<List<ShopOrder>> getAllShopOrders() {
        return ResponseEntity.ok(shopOrderService.getAllShopOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopOrder> getShopOrderById(@PathVariable int id) {
        return ResponseEntity.ok(shopOrderService.getShopOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopOrder> updateShopOrder(@PathVariable int id, @RequestBody ShopOrder shopOrder) {
        shopOrder.setId(id);
        return ResponseEntity.ok(shopOrderService.updateShopOrder(shopOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShopOrder(@PathVariable int id) {
        shopOrderService.deleteShopOrder(id);
        return ResponseEntity.noContent().build();
    }
}
