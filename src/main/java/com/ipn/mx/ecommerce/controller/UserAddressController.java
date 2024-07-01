package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.UserAddress;
import com.ipn.mx.ecommerce.service.interfaces.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user_addresses")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping
    public ResponseEntity<UserAddress> createUserAddress(@RequestBody UserAddress userAddress) {
        UserAddress savedUserAddress = userAddressService.saveUserAddress(userAddress);
        return ResponseEntity.ok(savedUserAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> getUserAddressById(@PathVariable int id) {
        Optional<UserAddress> userAddress = userAddressService.getUserAddressById(id);
        return userAddress.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserAddress>> getAllUserAddresses() {
        List<UserAddress> userAddresses = userAddressService.getAllUserAddresses();
        return ResponseEntity.ok(userAddresses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable int id) {
        userAddressService.deleteUserAddress(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAddress>> getUserAddressesByUserId(@PathVariable int userId) {
        List<UserAddress> userAddresses = userAddressService.getUserAddressesByUserId(userId);
        return ResponseEntity.ok(userAddresses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAddress> updateUserAddress(@PathVariable int id, @RequestBody UserAddress userAddressDetails) {
        Optional<UserAddress> updatedUserAddress = userAddressService.updateUserAddress(id, userAddressDetails);
        return updatedUserAddress.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
