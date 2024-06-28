package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.Address;
import com.ipn.mx.ecommerce.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        System.out.println("Address received: " + address);
        return addressService.save(address);
    }
    

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable int id) {
        return addressService.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable int id, @RequestBody Address addressDetails) {
        Address address = addressService.findById(id).orElse(null);
        if (address != null) {
            address.setUnitNumber(addressDetails.getUnitNumber());
            address.setStreetNumber(addressDetails.getStreetNumber());
            address.setAddressLine1(addressDetails.getAddressLine1());
            address.setAddressLine2(addressDetails.getAddressLine2());
            address.setCity(addressDetails.getCity());
            address.setRegion(addressDetails.getRegion());
            address.setPostalCode(addressDetails.getPostalCode());
            address.setCountryId(addressDetails.getCountryId());
            return addressService.save(address);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteById(id);
    }
}
