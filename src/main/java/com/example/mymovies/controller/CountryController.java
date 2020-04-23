package com.example.mymovies.controller;

import com.example.mymovies.model.Country;
import com.example.mymovies.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

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
        return "redirect:/";
    }
}
