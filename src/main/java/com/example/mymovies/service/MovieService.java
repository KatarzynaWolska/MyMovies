package com.example.mymovies.service;

import com.example.mymovies.model.*;
import com.example.mymovies.repository.MovieAwardRepository;
import com.example.mymovies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieService {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    MovieAwardRepository awardRepo;

    @GetMapping(path = "/movies")
    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }

    @PostMapping(path = "/movies", consumes = {"application/json"})
    public void addMovie(@RequestBody Movie movie) {
        movieRepo.save(movie);
    }

    @GetMapping(path = "/movies/{mid}")
    public Optional<Movie> getMovie(@PathVariable("mid") Integer mid) {
        return movieRepo.findById(mid);
    }

    @DeleteMapping(path = "/movies/{mid}")
    public void deleteMovie(@PathVariable("mid") Integer mid) {
        Movie movie = movieRepo.getOne(mid);
        movieRepo.delete(movie); // chyba się usuwa z Set<>
    }

    @PostMapping(path = "/movies/{mid}/awards")
    public void addMovieAward(@RequestBody Award award, @PathVariable("mid") Integer mid) {
        Movie movie = movieRepo.getOne(mid);
        award.setMovie(movie);
        awardRepo.save(award);
    }

}
