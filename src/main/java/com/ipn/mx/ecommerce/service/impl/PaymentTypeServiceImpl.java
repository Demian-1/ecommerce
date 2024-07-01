package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.PaymentType;
import com.ipn.mx.ecommerce.repository.PaymentTypeRepository;
import com.ipn.mx.ecommerce.service.interfaces.PaymentTypeService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    public PaymentType savePaymentType(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public List<PaymentType> getAllPaymentTypes() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public PaymentType getPaymentTypeById(int id) {
        return paymentTypeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PaymentType updatePaymentType(PaymentType paymentType) {
        PaymentType existingPaymentType = paymentTypeRepository.findById(paymentType.getId()).orElse(null);

        if (existingPaymentType != null) {
            existingPaymentType.setTypeName(paymentType.getTypeName());

            // Actualiza la colección existente en lugar de reemplazarla
            existingPaymentType.getUserPaymentMethods().clear();
            if (paymentType.getUserPaymentMethods() != null) {
                existingPaymentType.getUserPaymentMethods().addAll(paymentType.getUserPaymentMethods());
            }

            return paymentTypeRepository.save(existingPaymentType);
        } else {
            return null;
        }
    }

    @Override
    public void deletePaymentType(int id) {
        paymentTypeRepository.deleteById(id);
    }
}
