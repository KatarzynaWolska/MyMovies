package com.example.mymovies.controller;

import com.example.mymovies.model.Actor;
import com.example.mymovies.model.Country;
import com.example.mymovies.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/countries")
    public String getCountries(Model model) {
        List<Country> countries = countryService.getCountries();
        model.addAttribute("countries", countries);
        return "countries";
    }

    @RequestMapping("/addCountry")
    public String addCountry(Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        return "add_country";
    }

    @RequestMapping(value = "/saveCountry", method = RequestMethod.POST)
    public String saveCountry(@ModelAttribute("country") Country country) {
        countryService.addCountry(country);
        return "redirect:/";
    }

    @RequestMapping("/deleteCountry/{cid}")
    public String deleteCountry(@PathVariable("cid") Integer cid) {
        countryService.deleteCountry(cid);
        return "redirect:/countries";
    }

    @RequestMapping("/countries/{cid}")
    public String getCountry(@PathVariable("cid") Integer cid, Model model) {
        Optional<Country> country = countryService.getCountry(cid);
        country.ifPresent(c -> model.addAttribute("country", c));
        country.ifPresent(c -> model.addAttribute("directors", c.getDirectors()));
        country.ifPresent(c -> model.addAttribute("actors", c.getActors()));
        return "country_details";
    }

    @RequestMapping("/editCountry/{cid}")
    public String editCountry(@PathVariable("cid") Integer cid, Model model) {
        Optional<Country> country = countryService.getCountry(cid);
        country.ifPresent(c -> model.addAttribute("country", c));
        return "edit_country";
    }
}
