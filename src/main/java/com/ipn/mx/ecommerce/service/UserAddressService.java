package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> findAll();
    UserAddress findById(int id);
    UserAddress save(UserAddress userAddress);
    void deleteById(int id);
}
