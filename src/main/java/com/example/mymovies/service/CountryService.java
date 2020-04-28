package com.example.mymovies.service;

import com.example.mymovies.model.Country;
import com.example.mymovies.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryService {

    @Autowired
    private CountryRepository countryRepo;

    public List<Country> getCountries() {
        return countryRepo.findAll();
    }

    public void addCountry(Country country) {
        countryRepo.save(country);
    }

    public void deleteCountry(Integer id) {
        Country country = countryRepo.getOne(id);
        countryRepo.delete(country);
    }

    public Optional<Country> getCountry(Integer cid) {
        return countryRepo.findById(cid);
    }
}
