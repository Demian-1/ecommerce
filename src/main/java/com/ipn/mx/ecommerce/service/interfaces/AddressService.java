package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> findAll();
    Optional<Address> findById(int id);
    Address save(Address address);
    void deleteById(int id);
}
