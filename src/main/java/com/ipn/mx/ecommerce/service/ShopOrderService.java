package com.ipn.mx.ecommerce.service;


import com.ipn.mx.ecommerce.model.ShopOrder;

import java.util.List;
import java.util.Optional;

public interface ShopOrderService {
    List<ShopOrder> findAll();
    Optional<ShopOrder> findById(int id);
    ShopOrder save(ShopOrder address);
    void delete(ShopOrder shopOrder);
}
