package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.Category;
import com.ipn.mx.ecommerce.model.Product;
import com.ipn.mx.ecommerce.service.interfaces.CategoryService;
import com.ipn.mx.ecommerce.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        try {
            List<Product> res = productService.getAll();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        try {
            Optional<Product> res = productService.getById(id);
            if (res.isPresent()) {
                return new ResponseEntity<>(res.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<Product> createProductForCategory(@PathVariable int categoryId, @RequestBody Product product) {
        try {
            Optional<Category> optionalCategory = categoryService.getById(categoryId);
            if (optionalCategory.isPresent()) {
                Category Category = optionalCategory.get();
                product.setCategory(Category);
                Product newProduct = productService.save(product);
                return new ResponseEntity<>(newProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.save(product));
        } catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        try {
            Optional<Product> optionalProduct = productService.getById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setName(productDetails.getName());
                product.setDescription(productDetails.getDescription());
                product.setImage(productDetails.getImage());
                if(productDetails.getCategory() != null){
                    product.setCategory(productDetails.getCategory());
                }
                return ResponseEntity.ok(productService.save(product));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable int id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
