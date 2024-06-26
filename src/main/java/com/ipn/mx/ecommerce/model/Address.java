package com.ipn.mx.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "unit_number", length = 240)
    private String unitNumber;

    @Column(name = "street_number", length = 240)
    private String streetNumber;

    @Column(name = "address_line1", length = 240, nullable = false)
    private String addressLine1;

    @Column(name = "address_line2", length = 240)
    private String addressLine2;

    @Column(name = "city", length = 240, nullable = false)
    private String city;

    @Column(name = "region", length = 240, nullable = false)
    private String region;

    @Column(name = "postal_code", length = 20, nullable = false)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserAddress> userAddresses;
}
