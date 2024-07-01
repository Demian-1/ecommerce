package com.ipn.mx.ecommerce.controller;


import com.ipn.mx.ecommerce.model.ShopOrder;
import com.ipn.mx.ecommerce.service.impl.ShopOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api/shopOrder")
public class ShopOrderController {
    @Autowired
    private ShopOrderServiceImpl shopOrderService;

    @GetMapping
    public List<ShopOrder> getAll() {
        return shopOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopOrder> getById(@PathVariable int id) {
        Optional<ShopOrder> orderStatus = shopOrderService.findById(id);
        if(orderStatus.isPresent()) {
            return ResponseEntity.ok(orderStatus.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ShopOrder create(@RequestBody ShopOrder orderStatus) {
        return shopOrderService.save(orderStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopOrder> update(@PathVariable int id, @RequestBody ShopOrder shopOrder) {
        Optional<ShopOrder> optionalShopOrder = shopOrderService.findById(id);
        if(optionalShopOrder.isPresent()) {
            ShopOrder shopOrderAux = optionalShopOrder.get();

            shopOrderAux.setOrderDate(shopOrder.getOrderDate());
            shopOrderAux.setAddress(shopOrder.getAddress());
            shopOrderAux.setShippingMetod(shopOrder.getShippingMetod());
            shopOrderAux.setOrderTotal(shopOrder.getOrderTotal());
            shopOrderAux.setOrderStatus(shopOrder.getOrderStatus());


            return ResponseEntity.ok(shopOrderService.save(shopOrderAux));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<ShopOrder> optionalOrderStatus = shopOrderService.findById(id);
        if (optionalOrderStatus.isPresent()) {
            shopOrderService.delete(optionalOrderStatus.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
