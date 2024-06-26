package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.Address;
import com.ipn.mx.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        Address address = addressService.findById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address addressDetails) {
        Address address = addressService.findById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        address.setUnitNumber(addressDetails.getUnitNumber());
        address.setStreetNumber(addressDetails.getStreetNumber());
        address.setAddressLine1(addressDetails.getAddressLine1());
        address.setAddressLine2(addressDetails.getAddressLine2());
        address.setCity(addressDetails.getCity());
        address.setRegion(addressDetails.getRegion());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setCountry(addressDetails.getCountry());
        Address updatedAddress = addressService.save(address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
        Address address = addressService.findById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
