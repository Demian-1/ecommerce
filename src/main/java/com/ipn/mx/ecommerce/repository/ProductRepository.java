package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.Country;
import com.ipn.mx.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
