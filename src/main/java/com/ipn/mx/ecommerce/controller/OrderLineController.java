package com.ipn.mx.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ipn.mx.ecommerce.model.OrderLine;
import com.ipn.mx.ecommerce.service.interfaces.OrderLineService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-lines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    @Autowired
    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping
    public ResponseEntity<List<OrderLine>> getAllOrderLines() {
        return ResponseEntity.ok(orderLineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderLine>> getOrderLineById(@PathVariable int id) {
        return orderLineService.findById(id) != null ? 
            ResponseEntity.ok(orderLineService.findById(id)) :
            ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderLine> createOrderLine(@RequestBody OrderLine orderLine) {
        return ResponseEntity.ok(orderLineService.save(orderLine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderLine> updateOrderLine(@PathVariable int id, @RequestBody OrderLine orderLine) {
        Optional<OrderLine> existingOrderLine = orderLineService.findById(id);
        if (existingOrderLine == null) {
            return ResponseEntity.notFound().build();
        }
        orderLine.setId(id);
        return ResponseEntity.ok(orderLineService.save(orderLine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderLine(@PathVariable int id) {
        orderLineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
