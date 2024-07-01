package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.Product;
import com.ipn.mx.ecommerce.repository.ProductRepository;
import com.ipn.mx.ecommerce.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
