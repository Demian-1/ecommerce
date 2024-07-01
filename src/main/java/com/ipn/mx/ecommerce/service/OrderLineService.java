package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.OrderLine;
import com.ipn.mx.ecommerce.model.ShopOrder;

import java.util.List;
import java.util.Optional;

public interface OrderLineService {
    List<OrderLine> findAll();
    Optional<OrderLine> findById(int id);
    OrderLine save(OrderLine address);
    void delete(OrderLine orderLine);
}

