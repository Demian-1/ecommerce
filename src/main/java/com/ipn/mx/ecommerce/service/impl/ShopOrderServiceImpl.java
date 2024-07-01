package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.ShopOrder;
import com.ipn.mx.ecommerce.repository.ShopOrderRepository;
import com.ipn.mx.ecommerce.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {
    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Override
    public List<ShopOrder> findAll() {
        return shopOrderRepository.findAll();
    }

    @Override
    public Optional<ShopOrder> findById(int id) {
        return shopOrderRepository.findById(id);
    }

    @Override
    public ShopOrder save(ShopOrder shopOrder) {
        return shopOrderRepository.save(shopOrder);
    }

    @Override
    public void delete(ShopOrder shopOrder) {
        shopOrderRepository.delete(shopOrder);
    }
}
