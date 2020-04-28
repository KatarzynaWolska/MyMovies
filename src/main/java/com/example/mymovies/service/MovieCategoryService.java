package com.example.mymovies.service;

import com.example.mymovies.model.MovieCategory;
import com.example.mymovies.repository.MovieCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieCategoryService {

    @Autowired
    private MovieCategoryRepository movieCategoryRepo;

    //@GetMapping(path = "/categories")
    public List<MovieCategory> getMoviesCategories() {
        return movieCategoryRepo.findAll();
    }

    //@PostMapping(path = "/categories", consumes = {"application/json"})
    public void addCategory(@RequestBody MovieCategory category) {
        movieCategoryRepo.save(category);
    }

    //@GetMapping(path = "/categories/{cid}")
    public Optional<MovieCategory> getMovieCategory(@PathVariable("cid") Integer cid) {
        return movieCategoryRepo.findById(cid);
    }

    //@DeleteMapping(path = "/categories/{cid}")
    public void deleteMovieCategory(@PathVariable("cid") Integer cid) {
        MovieCategory category = movieCategoryRepo.getOne(cid);
        movieCategoryRepo.delete(category); // chyba się usuwa z Set<>
    }
}
