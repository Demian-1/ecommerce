package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.Category;
import com.ipn.mx.ecommerce.model.Country;
import com.ipn.mx.ecommerce.service.CategoryService;
import com.ipn.mx.ecommerce.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryService.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryService.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryService.deleteById(id);
    }
}
