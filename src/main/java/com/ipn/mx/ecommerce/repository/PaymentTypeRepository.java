package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
}
