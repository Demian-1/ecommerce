package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.OrderLine;
import com.ipn.mx.ecommerce.service.impl.OrderLineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api/orderLine")
public class OrderLineController {
    @Autowired
    private OrderLineServiceImpl orderLineService;

    @GetMapping
    public List<OrderLine> getAll() {
        return orderLineService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderLine> getById(@PathVariable int id) {
        Optional<OrderLine> orderStatus = orderLineService.findById(id);
        if(orderStatus.isPresent()) {
            return ResponseEntity.ok(orderStatus.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public OrderLine create(@RequestBody OrderLine orderStatus) {
        return orderLineService.save(orderStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderLine> update(@PathVariable int id, @RequestBody OrderLine orderLine) {
        Optional<OrderLine> optionalOrderLine = orderLineService.findById(id);
        if(optionalOrderLine.isPresent()) {
            OrderLine orderLineAux = optionalOrderLine.get();

            orderLineAux.setShopOrder(orderLine.getShopOrder());
            orderLineAux.setPrice(orderLine.getPrice());
            orderLineAux.setQty(orderLine.getQty());
            orderLineAux.setProductItem(orderLine.getProductItem());

            return ResponseEntity.ok(orderLineService.save(orderLineAux));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<OrderLine> optionalOrderStatus = orderLineService.findById(id);
        if (optionalOrderStatus.isPresent()) {
            orderLineService.delete(optionalOrderStatus.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
