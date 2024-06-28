package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.SiteUser;
import com.ipn.mx.ecommerce.service.interfaces.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class SiteUserController {

    @Autowired
    private SiteUserService siteUserService;

    @GetMapping("/{id}")
    public SiteUser getCountryById(@PathVariable int id) {
        return siteUserService.getUserById(id).orElse(null);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SiteUser user) {
        try {
            SiteUser registeredUser = siteUserService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String emailAddress, @RequestParam String password) {
        return siteUserService.loginUser(emailAddress, password)
                .map(user -> ResponseEntity.ok((Object) user))
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SiteUser>> getAllUsers() {
        return ResponseEntity.ok(siteUserService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody SiteUser updatedUser) {
        return siteUserService.updateUser(id, updatedUser)
                .map(user -> ResponseEntity.ok((Object) user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        if (siteUserService.deleteUser(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
