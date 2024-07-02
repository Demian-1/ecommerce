package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.UserAddress;
import com.ipn.mx.ecommerce.model.UserPaymentMethod;
import com.ipn.mx.ecommerce.repository.UserPaymentMethodRepository;
import com.ipn.mx.ecommerce.service.interfaces.UserPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPaymentMethodServiceImpl implements UserPaymentMethodService {

    @Autowired
    private UserPaymentMethodRepository userPaymentMethodRepository;

    @Override
    public UserPaymentMethod saveUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        return userPaymentMethodRepository.save(userPaymentMethod);
    }

    @Override
    public List<UserPaymentMethod> getAllUserPaymentMethods() {
        return userPaymentMethodRepository.findAll();
    }

    @Override
    public UserPaymentMethod getUserPaymentMethodById(int id) {
        return userPaymentMethodRepository.findById(id).orElse(null);
    }

    @Override
    public UserPaymentMethod updateUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        return userPaymentMethodRepository.save(userPaymentMethod);
    }

    @Override
    public void deleteUserPaymentMethod(int id) {
        userPaymentMethodRepository.deleteById(id);
    }

    @Override
    public List<UserPaymentMethod> getUserPaymentMethodByUserId(int userId) {
        return userPaymentMethodRepository.findByUserId(userId);
    }
}
