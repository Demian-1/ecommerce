package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderStatusService {
    List<OrderStatus> getAll();
    Optional<OrderStatus> getById(int id);
    OrderStatus save(OrderStatus orderStatus);
    void delete(OrderStatus orderStatus);
}
