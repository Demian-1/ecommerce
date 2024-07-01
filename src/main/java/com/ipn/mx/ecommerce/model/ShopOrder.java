package com.ipn.mx.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ShopOrder")
public class ShopOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "sipping_adress", nullable = false)
    private Address address;

    @Column(name = "shipping_method", nullable = false,length = 100)
    private String ShippingMetod;

    @Column(name = "oreder_total", nullable = false)
    private Float orderTotal;

    @Column(name = "order_status", nullable = false, length = 50)
    private Date orderStatus;

    //todo userPayment method
    //@OneToMany(mappedBy = "UserPaymentMethod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnore
    //private List<UserPaymentMethod> userPaymentMethod;

}

