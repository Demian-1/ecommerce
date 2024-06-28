package com.ipn.mx.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "site_user")
public class SiteUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email_address", length = 240, nullable = false)
    private String emailAddress;

    @Column(name = "user_name", length = 240, nullable = false)
    private String userName;

    @Column(name = "phone_number", length = 240, nullable = false)
    private String phoneNumber;

    @Column(name = "password", length = 240, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserAddress> userAddresses;
}
