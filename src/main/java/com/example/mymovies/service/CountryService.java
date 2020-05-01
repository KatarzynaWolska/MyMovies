package com.example.mymovies.service;

import com.example.mymovies.model.Actor;
import com.example.mymovies.model.Country;
import com.example.mymovies.model.Director;
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
import java.util.Set;

@Service
@Transactional
public class CountryService {

    @Autowired
    private CountryRepository countryRepo;

    public List<Country> getCountries() {
        return countryRepo.findAll();
    }

    public void addCountry(Country country) {
        System.out.println(country.getId());
        countryRepo.save(country);
    }

    public void deleteCountry(Integer id) {
        Country country = countryRepo.getOne(id);

        Set<Actor> actors = country.getActors();
        actors.forEach(actor -> actor.setCountry(null));

        Set<Director> directors = country.getDirectors();
        directors.forEach(director -> director.setCountry(null));

        countryRepo.delete(country);
    }

    public Optional<Country> getCountry(Integer cid) {
        return countryRepo.findById(cid);
    }
}
