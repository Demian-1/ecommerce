package com.ipn.mx.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shop_order")
public class ShopOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address address;

    @Column(name = "shipping_method", nullable = false,length = 100)
    private String shippingMethod;

    @Column(name = "order_total", nullable = false)
    private Float orderTotal;

    @Column(name = "order_status", nullable = false, length = 50)
    private String orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserPaymentMethod> userPaymentMethods; 
}

