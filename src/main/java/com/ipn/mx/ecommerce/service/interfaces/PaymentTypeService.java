package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.PaymentType;
import java.util.List;

public interface PaymentTypeService {
    PaymentType savePaymentType(PaymentType paymentType);
    List<PaymentType> getAllPaymentTypes();
    PaymentType getPaymentTypeById(int id);
    PaymentType updatePaymentType(PaymentType paymentType);
    void deletePaymentType(int id);
}
