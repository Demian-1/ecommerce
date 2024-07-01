package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();
    Optional<Product> getById(int id);
    Product save(Product product);
    void deleteById(int id);
}
