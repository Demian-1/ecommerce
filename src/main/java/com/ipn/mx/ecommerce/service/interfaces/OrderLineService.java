package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.OrderLine;

import java.util.List;
import java.util.Optional;

public interface OrderLineService {
    List<OrderLine> findAll();
    Optional<OrderLine> findById(int id);
    OrderLine save(OrderLine address);
    void deleteById(int id);
}

