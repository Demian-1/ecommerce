package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.Country;
import com.ipn.mx.ecommerce.model.Product;
import com.ipn.mx.ecommerce.repository.ProductRepository;
import com.ipn.mx.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
