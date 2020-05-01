package com.example.mymovies.controller;

import com.example.mymovies.model.*;
import com.example.mymovies.service.CountryService;
import com.example.mymovies.service.DirectorAwardService;
import com.example.mymovies.service.DirectorService;
import com.example.mymovies.service.MovieService;
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

    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorAwardService awardService;

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
        director.ifPresent(d -> model.addAttribute("awards", d.getAwards()));
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

    @RequestMapping("/addDirectorAward/{did}")
    public String addDirectorAward(@PathVariable("did") Integer did, Model model) {
        Optional<Director> director = directorService.getDirector(did);
        DirectorAward award = new DirectorAward();
        director.ifPresent(d -> award.setDirector(d));
        director.ifPresent(d -> d.getAwards().add(award));
        director.ifPresent(d -> model.addAttribute("movies", d.getMovies()));
        model.addAttribute("award", award);
        return "add_director_award";
    }

    @RequestMapping("/saveDirectorAward")
    public String saveDirectorAward(@ModelAttribute("award") DirectorAward award) {
        awardService.addDirectorAward(award);
        return "redirect:/";
    }
}
