package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.UserAddress;
import com.ipn.mx.ecommerce.model.UserPaymentMethod;
import com.ipn.mx.ecommerce.service.interfaces.UserPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-payment-methods")
public class UserPaymentMethodController {

    @Autowired
    private UserPaymentMethodService userPaymentMethodService;

    @PostMapping
    public ResponseEntity<UserPaymentMethod> createUserPaymentMethod(@RequestBody UserPaymentMethod userPaymentMethod) {
        System.out.println("Received UserPaymentMethod: " + userPaymentMethod);
        return ResponseEntity.ok(userPaymentMethodService.saveUserPaymentMethod(userPaymentMethod));
    }

    @GetMapping
    public ResponseEntity<List<UserPaymentMethod>> getAllUserPaymentMethods() {
        return ResponseEntity.ok(userPaymentMethodService.getAllUserPaymentMethods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPaymentMethod> getUserPaymentMethodById(@PathVariable int id) {
        return ResponseEntity.ok(userPaymentMethodService.getUserPaymentMethodById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPaymentMethod> updateUserPaymentMethod(@PathVariable int id, @RequestBody UserPaymentMethod userPaymentMethod) {
        userPaymentMethod.setId(id);
        return ResponseEntity.ok(userPaymentMethodService.updateUserPaymentMethod(userPaymentMethod));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPaymentMethod(@PathVariable int id) {
        userPaymentMethodService.deleteUserPaymentMethod(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPaymentMethod>> getUserPaymentMethodByUserId(@PathVariable int userId) {
        List<UserPaymentMethod> userPaymentMethods = userPaymentMethodService.getUserPaymentMethodByUserId(userId);
        return ResponseEntity.ok(userPaymentMethods);
    }
}
