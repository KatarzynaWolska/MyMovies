package com.example.mymovies.service;

import com.example.mymovies.model.*;
import com.example.mymovies.repository.DirectorAwardRepository;
import com.example.mymovies.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepo;

    @Autowired
    private DirectorAwardRepository awardRepo;

    @GetMapping(path = "/directors")
    public List<Director> getDirectors() {
        return directorRepo.findAll();
    }

    @PostMapping(path = "/directors", consumes = {"application/json"})
    public void addDirector(@RequestBody Director director) {
        directorRepo.save(director);
        director.getCountry().getDirectors().add(director);
    }

    @GetMapping(path = "/directors/{did}")
    public Optional<Director> getDirector(@PathVariable("did") Integer did) {
        return directorRepo.findById(did);
    }

    @DeleteMapping(path = "/directors/{did}")
    public void deleteDirector(@PathVariable("did") Integer did) {
        Director director = directorRepo.getOne(did);
        directorRepo.delete(director); // chyba się usuwa z Set<>
    }

    @PostMapping(path = "/directors/{did}/awards")
    public void addDirectorAward(@RequestBody Award award, @PathVariable("did") Integer did) {
        Director director = directorRepo.getOne(did);
        DirectorAward directorAward = new DirectorAward(award.getName(), award.getMovie(), director);
        awardRepo.save(directorAward);
    }
}
