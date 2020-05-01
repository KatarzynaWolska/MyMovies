package com.example.mymovies.controller;

import com.example.mymovies.model.Actor;
import com.example.mymovies.model.Country;
import com.example.mymovies.model.Director;
import com.example.mymovies.service.CountryService;
import com.example.mymovies.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @Autowired
    private CountryService countryService;

    @RequestMapping("/directors")
    public String getDirectors(Model model) {
        List<Director> directors = directorService.getDirectors();
        model.addAttribute("directors", directors);
        return "directors";
    }

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

    @RequestMapping("/directors/{did}")
    public String getDirector(@PathVariable("did") Integer did, Model model) {
        Optional<Director> director = directorService.getDirector(did);
        director.ifPresent(d -> model.addAttribute("director", d));
        director.ifPresent(d -> model.addAttribute("movies", d.getMovies()));
        return "director_details";
    }

    @RequestMapping("/deleteDirector/{did}")
    public String deleteDirector(@PathVariable("did") Integer did) {
        directorService.deleteDirector(did);
        return "redirect:/directors";
    }

    @RequestMapping("/editDirector/{did}")
    public String editDirector(@PathVariable("did") Integer did, Model model) {
        Optional<Director> director = directorService.getDirector(did);
        director.ifPresent(d -> model.addAttribute("director", d));
        model.addAttribute("countries", countryService.getCountries());
        return "edit_director";
    }
}
