package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.Category;
import com.ipn.mx.ecommerce.model.Country;
import com.ipn.mx.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCountry(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping
    public List<Category> getAllCountries() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getCountryById(@PathVariable int id) {
        return categoryService.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Category updateCountry(@PathVariable int id, @RequestBody Category categorydatails) {
        Category category = categoryService.findById(id).orElse(null);
        if (category != null) {
            category = (Category) categorydatails;
            return categoryService.save(category);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable int id) {
        categoryService.deleteById(id);
    }
}
