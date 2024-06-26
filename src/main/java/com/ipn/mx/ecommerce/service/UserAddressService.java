package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.UserAddress;
import java.util.List;
import java.util.Optional;

public interface UserAddressService {
    UserAddress saveUserAddress(UserAddress userAddress);
    Optional<UserAddress> getUserAddressById(int id);
    List<UserAddress> getAllUserAddresses();
    void deleteUserAddress(int id);
    List<UserAddress> getUserAddressesByUserId(int userId);
    Optional<UserAddress> updateUserAddress(int id, UserAddress userAddressDetails);
}
