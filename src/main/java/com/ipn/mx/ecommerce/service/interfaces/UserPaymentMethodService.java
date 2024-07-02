package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.UserPaymentMethod;
import java.util.List;

public interface UserPaymentMethodService {
    UserPaymentMethod saveUserPaymentMethod(UserPaymentMethod userPaymentMethod);
    List<UserPaymentMethod> getAllUserPaymentMethods();
    UserPaymentMethod getUserPaymentMethodById(int id);
    UserPaymentMethod updateUserPaymentMethod(UserPaymentMethod userPaymentMethod);
    void deleteUserPaymentMethod(int id);
    List<UserPaymentMethod> getUserPaymentMethodByUserId(int userId);
}
