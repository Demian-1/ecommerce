package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(int id);

    Country save(Country country);

    List<Country> saveAll(List<Country> countries);

    void deleteById(int id);
}
