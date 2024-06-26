package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
