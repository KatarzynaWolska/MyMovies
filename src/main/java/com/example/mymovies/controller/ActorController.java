package com.example.mymovies.controller;

import com.example.mymovies.model.Actor;
import com.example.mymovies.model.Country;
import com.example.mymovies.service.ActorService;
import com.example.mymovies.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private CountryService countryService;

    @RequestMapping("/actors")
    public String getActors(Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);
        return "actors";
    }

    @RequestMapping("/addActor")
    public String addActor(Model model) {
        Actor actor = new Actor();
        List<Country> countries = countryService.getCountries();
        model.addAttribute("actor", actor);
        model.addAttribute("countries", countries);
        return "add_actor";
    }

    @RequestMapping("/saveActor")
    public String saveActor(@ModelAttribute("actor") Actor actor) {
        actorService.addActor(actor);
        return "redirect:/";
    }

    @RequestMapping("/actors/{aid}")
    public String getActor(@PathVariable("aid") Integer aid, Model model) {
        Optional<Actor> actor = actorService.getActor(aid);
        actor.ifPresent(ac -> model.addAttribute("actor", ac));
        return "actor_details";
    }
}
