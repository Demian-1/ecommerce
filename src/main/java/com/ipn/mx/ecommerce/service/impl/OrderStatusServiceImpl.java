package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.OrderStatus;
import com.ipn.mx.ecommerce.repository.OrderStatusRepository;
import com.ipn.mx.ecommerce.service.interfaces.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepo;

    @Override
    public List<OrderStatus> getAll() {
        return orderStatusRepo.findAll();
    }

    @Override
    public Optional<OrderStatus> getById(int id) {
        return orderStatusRepo.findById(id);
    }

    @Override
    public OrderStatus save(OrderStatus orderStatus) {
        return orderStatusRepo.save(orderStatus);
    }

    @Override
    public void delete(OrderStatus orderStatus) {
        orderStatusRepo.delete(orderStatus);
    }
}
