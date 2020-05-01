package com.example.mymovies.controller;

import com.example.mymovies.model.Actor;
import com.example.mymovies.model.ActorAward;
import com.example.mymovies.model.Country;
import com.example.mymovies.model.MovieCategory;
import com.example.mymovies.service.ActorAwardService;
import com.example.mymovies.service.ActorService;
import com.example.mymovies.service.CountryService;
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
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorAwardService actorAwardService;

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
        actor.ifPresent(ac -> model.addAttribute("movies", ac.getMovies()));
        actor.ifPresent(ac -> model.addAttribute("awards", ac.getAwards()));
        return "actor_details";
    }

    @RequestMapping("/deleteActor/{aid}")
    public String deleteActor(@PathVariable("aid") Integer aid) {
        Optional<Actor> actor = actorService.getActor(aid);
        actor.ifPresent(ac -> ac.getMovies().forEach(movie -> movie.setActors(null)));
        actorService.deleteActor(aid);
        return "redirect:/actors";
    }

    @RequestMapping("/editActor/{aid}")
    public String editActor(@PathVariable("aid") Integer aid, Model model) {
        Optional<Actor> actor = actorService.getActor(aid);
        actor.ifPresent(ac -> model.addAttribute("actor", ac));
        model.addAttribute("countries", countryService.getCountries());
        model.addAttribute("movies", movieService.getMovies());
        return "edit_actor";
    }

    @RequestMapping("/addActorAward/{aid}")
    public String addActorAward(@PathVariable("aid") Integer aid, Model model) {
        Optional<Actor> actor = actorService.getActor(aid);
        ActorAward award = new ActorAward();
        actor.ifPresent(a -> award.setActor(a));
        actor.ifPresent(a -> a.getAwards().add(award));
        model.addAttribute("award", award);
        model.addAttribute("movies", movieService.getMovies());
        return "add_actor_award";
    }

    @RequestMapping("/saveActorAward")
    public String saveActorAward(@ModelAttribute("award") ActorAward award) {
        actorAwardService.addActorAward(award);
        return "redirect:/";
    }
}
