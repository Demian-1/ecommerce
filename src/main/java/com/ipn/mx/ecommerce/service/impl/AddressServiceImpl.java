package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.Address;
import com.ipn.mx.ecommerce.repository.AddressRepository;
import com.ipn.mx.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(int id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }
}
