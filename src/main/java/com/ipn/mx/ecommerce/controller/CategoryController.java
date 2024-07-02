package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.Category;
import com.ipn.mx.ecommerce.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

   @PostMapping("/batch")
    public List<Category> createcCategories(@RequestBody List<Category> categories) {
        return service.saveAll(categories);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        try {
            List<Category> categories = service.getAll();
            if(categories.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id){
        try{
            Optional<Category> categoryOpt = service.getById(id);

            if(categoryOpt.isPresent()){
                return new ResponseEntity<>(categoryOpt.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category){
        try{
            Category res = service.save(category);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable int id, @RequestBody Category category){
        try {
            Optional<Category> categoryOpt = service.getById(id);
            if(categoryOpt.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Category aux = categoryOpt.get();
            aux.setName(category.getName());

            Category res = service.save(aux);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable int id){
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}