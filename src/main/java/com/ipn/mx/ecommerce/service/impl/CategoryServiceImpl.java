package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.Category;
import com.ipn.mx.ecommerce.repository.CategoryRepository;
import com.ipn.mx.ecommerce.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
