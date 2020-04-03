package com.example.mymovies.controller;

import com.example.mymovies.model.Country;
import com.example.mymovies.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository countryRepo;

    @GetMapping(path = "/countries")
    public List<Country> getCountries() {
        System.out.println(countryRepo.findAll().get(0).toString());
        return countryRepo.findAll();
    }

    @PostMapping(path = "/countries", consumes = {"application/json"})
    public void addCountry(@RequestBody Country country) {
        countryRepo.save(country);
    }
}
