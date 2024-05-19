package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.OrderStatus;
import com.ipn.mx.ecommerce.service.impl.OrderStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api/orderstatus")
public class OrderStatusController {
    @Autowired
    private OrderStatusServiceImpl orderStatusService;

    @GetMapping
    public List<OrderStatus> getAll() {
        return orderStatusService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderStatus> getById(@PathVariable int id) {
        Optional<OrderStatus> orderStatus = orderStatusService.getById(id);
        if(orderStatus.isPresent()) {
            return ResponseEntity.ok(orderStatus.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public OrderStatus create(@RequestBody OrderStatus orderStatus) {
        return orderStatusService.save(orderStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderStatus> update(@PathVariable int id, @RequestBody OrderStatus orderStatus) {
        Optional<OrderStatus> optionalOrderStatus = orderStatusService.getById(id);
        if(optionalOrderStatus.isPresent()) {
            OrderStatus orderStatusAux = optionalOrderStatus.get();
            orderStatusAux.setStatus(orderStatus.getStatus());
            return ResponseEntity.ok(orderStatusService.save(orderStatusAux));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<OrderStatus> optionalOrderStatus = orderStatusService.getById(id);
        if (optionalOrderStatus.isPresent()) {
            orderStatusService.delete(optionalOrderStatus.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
