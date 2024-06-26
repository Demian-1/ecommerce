package com.ipn.mx.ecommerce.service;

import com.ipn.mx.ecommerce.model.SiteUser;
import com.ipn.mx.ecommerce.repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteUserService {

    @Autowired
    private SiteUserRepository siteUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SiteUser registerUser(SiteUser user) {
        if (siteUserRepository.findByEmailAddress(user.getEmailAddress()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return siteUserRepository.save(user);
    }

    public Optional<SiteUser> loginUser(String emailAddress, String password) {
        Optional<SiteUser> userOpt = siteUserRepository.findByEmailAddress(emailAddress);
        if (userOpt.isPresent()) {
            SiteUser user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public List<SiteUser> getAllUsers() {
        return siteUserRepository.findAll();
    }

    public Optional<SiteUser> updateUser(int id, SiteUser updatedUser) {
        return siteUserRepository.findById(id).map(user -> {
            user.setEmailAddress(updatedUser.getEmailAddress());
            user.setUserName(updatedUser.getUserName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            if (!updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            return siteUserRepository.save(user);
        });
    }

    public boolean deleteUser(int id) {
        if (siteUserRepository.existsById(id)) {
            siteUserRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
