package com.example.mymovies.controller;

import com.example.mymovies.model.Country;
import com.example.mymovies.model.Director;
import com.example.mymovies.service.CountryService;
import com.example.mymovies.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @Autowired
    private CountryService countryService;

    @RequestMapping("/addDirector")
    public String addDirector(Model model) {
        Director director = new Director();
        List<Country> countries = countryService.getCountries();
        model.addAttribute("director", director);
        model.addAttribute("countries", countries);
        return "add_director";
    }

    @RequestMapping("/saveDirector")
    public String saveDirector(@ModelAttribute("director") Director director) {
        directorService.addDirector(director);
        return "redirect:/";
    }
}
