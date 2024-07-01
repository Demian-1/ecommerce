package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.Category;
import com.ipn.mx.ecommerce.model.Country;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(int id);

    Category save(Category category);

    void deleteById(int id);
}
