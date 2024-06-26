package com.ipn.mx.ecommerce.service.impl;

import com.ipn.mx.ecommerce.model.Country;
import com.ipn.mx.ecommerce.repository.CountryRepository;
import com.ipn.mx.ecommerce.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(int id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public List<Country> saveAll(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    @Override
    public void deleteById(int id) {
        countryRepository.deleteById(id);
    }
}
