package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Integer> {
}
