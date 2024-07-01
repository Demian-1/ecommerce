package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.OrderLine;
import com.ipn.mx.ecommerce.repository.OrderLineRepository;
import com.ipn.mx.ecommerce.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineServiceImpl implements OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

    @Override
    public Optional<OrderLine> findById(int id) {
        return orderLineRepository.findById(id);
    }

    @Override
    public OrderLine save(OrderLine shopOrder) {
        return orderLineRepository.save(shopOrder);
    }

    @Override
    public void delete(OrderLine orderLine) {
        orderLineRepository.delete(orderLine);
    }
}
