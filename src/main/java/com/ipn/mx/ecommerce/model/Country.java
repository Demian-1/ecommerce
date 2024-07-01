package com.ipn.mx.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country_name", length = 240, nullable = false)
    private String countryName;

    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;
}
