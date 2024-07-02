package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.UserPaymentMethod;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Integer> {
    List<UserPaymentMethod> findByUserId(int userId);
}
