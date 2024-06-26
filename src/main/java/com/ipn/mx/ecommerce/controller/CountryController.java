package com.ipn.mx.ecommerce.controller;

import com.ipn.mx.ecommerce.model.Country;
import com.ipn.mx.ecommerce.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/batch")
    public List<Country> createCountries(@RequestBody List<Country> countries) {
        return countryService.saveAll(countries);
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryService.save(country);
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable int id) {
        return countryService.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable int id, @RequestBody Country countryDetails) {
        Country country = countryService.findById(id).orElse(null);
        if (country != null) {
            country.setCountryName(countryDetails.getCountryName());
            return countryService.save(country);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable int id) {
        countryService.deleteById(id);
    }
}
