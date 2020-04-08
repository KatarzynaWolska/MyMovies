package com.example.mymovies.controller;

import com.example.mymovies.model.Actor;
import com.example.mymovies.model.ActorAward;
import com.example.mymovies.model.Award;
import com.example.mymovies.repository.ActorAwardRepository;
import com.example.mymovies.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    private ActorRepository actorRepo;

    @Autowired
    private ActorAwardRepository awardRepo;

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
        actorRepo.delete(actor);
    }

    @PostMapping(path = "/actors/{aid}/awards")
    public void addActorAward(@RequestBody Award award, @PathVariable("aid") Integer aid) {
        Actor actor = actorRepo.getOne(aid);
        ActorAward actorAward = new ActorAward(award.getName(), award.getMovie(), actor);
        awardRepo.save(actorAward);
    }
}
