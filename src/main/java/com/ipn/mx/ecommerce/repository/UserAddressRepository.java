package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
}
