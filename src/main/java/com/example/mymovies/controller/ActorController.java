package com.example.mymovies.controller;

import com.example.mymovies.model.Actor;
import com.example.mymovies.model.Director;
import com.example.mymovies.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    ActorRepository actorRepo;

    @GetMapping(path = "/actors")
    public List<Actor> getActors() {
        return actorRepo.findAll();
    }

    @PostMapping(path = "/actors", consumes = {"application/json"})
    public void addActor(@RequestBody Actor actor) {
        actorRepo.save(actor);
        actor.getCountry().getActors().add(actor);
    }

    @GetMapping(path = "/actors/{aid}")
    public Optional<Actor> getActor(@PathVariable("aid") Integer aid) {
        return actorRepo.findById(aid);
    }

    @DeleteMapping(path = "/actors/{aid}")
    public void deleteActor(@PathVariable("aid") Integer aid) {
        Actor actor = actorRepo.getOne(aid);
        actorRepo.delete(actor); // chyba się usuwa z Set<>
    }
}
