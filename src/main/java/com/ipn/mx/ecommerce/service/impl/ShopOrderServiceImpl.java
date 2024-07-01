package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.ShopOrder;
import com.ipn.mx.ecommerce.repository.ShopOrderRepository;
import com.ipn.mx.ecommerce.service.interfaces.ShopOrderService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Override
    public ShopOrder saveShopOrder(ShopOrder shopOrder) {
        return shopOrderRepository.save(shopOrder);
    }

    @Override
    public List<ShopOrder> getAllShopOrders() {
        return shopOrderRepository.findAll();
    }

    @Override
    public Optional<ShopOrder> getShopOrderById(int id) {
        return shopOrderRepository.findById(id);
    }

    @Override
    @Transactional
    public ShopOrder updateShopOrder(ShopOrder shopOrder) {
        ShopOrder existingShopOrder = shopOrderRepository.findById(shopOrder.getId()).orElse(null);

        if (existingShopOrder != null) {
            existingShopOrder.setOrderDate(shopOrder.getOrderDate());
            existingShopOrder.setAddress(shopOrder.getAddress());
            existingShopOrder.setShippingMethod(shopOrder.getShippingMethod());
            existingShopOrder.setOrderTotal(shopOrder.getOrderTotal());
            existingShopOrder.setOrderStatus(shopOrder.getOrderStatus());

            // Maneja correctamente la colección de userPaymentMethods
            existingShopOrder.getUserPaymentMethods().clear();
            if (shopOrder.getUserPaymentMethods() != null) {
                existingShopOrder.getUserPaymentMethods().addAll(shopOrder.getUserPaymentMethods());
            }

            return shopOrderRepository.save(existingShopOrder);
        } else {
            return null;
        }
    }

    @Override
    public void deleteShopOrder(int id) {
        shopOrderRepository.deleteById(id);
    }
}
