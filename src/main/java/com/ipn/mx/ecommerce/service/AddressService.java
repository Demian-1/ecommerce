package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address findById(int id);
    Address save(Address address);
    void deleteById(int id);
}
