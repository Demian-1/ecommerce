package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.PaymentType;
import com.ipn.mx.ecommerce.service.interfaces.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-types")
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @PostMapping
    public ResponseEntity<PaymentType> createPaymentType(@RequestBody PaymentType paymentType) {
        return ResponseEntity.ok(paymentTypeService.savePaymentType(paymentType));
    }

    @GetMapping
    public ResponseEntity<List<PaymentType>> getAllPaymentTypes() {
        return ResponseEntity.ok(paymentTypeService.getAllPaymentTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentType> getPaymentTypeById(@PathVariable int id) {
        return ResponseEntity.ok(paymentTypeService.getPaymentTypeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentType> updatePaymentType(@PathVariable int id, @RequestBody PaymentType paymentType) {
        paymentType.setId(id);
        return ResponseEntity.ok(paymentTypeService.updatePaymentType(paymentType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentType(@PathVariable int id) {
        paymentTypeService.deletePaymentType(id);
        return ResponseEntity.noContent().build();
    }
}
