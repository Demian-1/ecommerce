package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.Country;
import com.ipn.mx.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(int id);

    Product save(Product product);

    void deleteById(int id);
}
