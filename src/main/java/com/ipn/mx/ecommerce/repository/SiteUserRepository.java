package com.ipn.mx.ecommerce.repository;

import com.ipn.mx.ecommerce.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Integer> {
    Optional<SiteUser> findByEmailAddress(String emailAddress);
}
