package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    Optional<Category> getById(int id);
    Category save(Category category);
    void deleteById(int id);
}
