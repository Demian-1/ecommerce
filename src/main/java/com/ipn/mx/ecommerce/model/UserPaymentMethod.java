package com.ipn.mx.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_payment_method")
public class UserPaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiteUser user;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", nullable = false)
    private PaymentType paymentType; 

    @Column(name = "provider", length = 240, nullable = false)
    private String provider;

    @Column(name = "account_number", length = 100, nullable = false)
    private String accountNumber;

    @Column(name = "expiry_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expiryDate;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ShopOrder order;
}
