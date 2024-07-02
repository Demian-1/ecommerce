package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.ShopOrder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {
    List<ShopOrder> findByUserId(int userId);
}
