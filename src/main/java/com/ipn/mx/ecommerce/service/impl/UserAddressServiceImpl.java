package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.UserAddress;
import com.ipn.mx.ecommerce.repository.UserAddressRepository;
import com.ipn.mx.ecommerce.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public List<UserAddress> findAll() {
        return userAddressRepository.findAll();
    }

    @Override
    public UserAddress findById(int id) {
        return userAddressRepository.findById(id).orElse(null);
    }

    @Override
    public UserAddress save(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    @Override
    public void deleteById(int id) {
        userAddressRepository.deleteById(id);
    }
}
