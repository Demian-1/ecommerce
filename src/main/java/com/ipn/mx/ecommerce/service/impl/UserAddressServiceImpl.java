package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.UserAddress;
import com.ipn.mx.ecommerce.repository.UserAddressRepository;
import com.ipn.mx.ecommerce.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public UserAddress saveUserAddress(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    @Override
    public Optional<UserAddress> getUserAddressById(int id) {
        return userAddressRepository.findById(id);
    }

    @Override
    public List<UserAddress> getAllUserAddresses() {
        return userAddressRepository.findAll();
    }

    @Override
    public void deleteUserAddress(int id) {
        userAddressRepository.deleteById(id);
    }

    @Override
    public List<UserAddress> getUserAddressesByUserId(int userId) {
        return userAddressRepository.findByUserId(userId);
    }
    
    @Override
    public Optional<UserAddress> updateUserAddress(int id, UserAddress userAddressDetails) {
        return userAddressRepository.findById(id).map(userAddress -> {
            userAddress.setAddress(userAddressDetails.getAddress());
            userAddress.setDefault(userAddressDetails.isDefault());
            return userAddressRepository.save(userAddress);
        });
    }
}
