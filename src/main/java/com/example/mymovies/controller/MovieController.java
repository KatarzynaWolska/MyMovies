package com.example.mymovies.controller;

import com.example.mymovies.model.*;
import com.example.mymovies.service.ActorService;
import com.example.mymovies.service.DirectorService;
import com.example.mymovies.service.MovieCategoryService;
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
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieCategoryService movieCategoryService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private ActorService actorService;

    @RequestMapping("/movies")
    public String getMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @RequestMapping("/addMovie")
    public String addMovie(Model model) {
        Movie movie = new Movie();
        List<MovieCategory> categories = movieCategoryService.getMoviesCategories();
        List<Director> directors = directorService.getDirectors();
        List<Actor> actors = actorService.getActors();
        model.addAttribute("movie", movie);
        model.addAttribute("categories", categories);
        model.addAttribute("directors", directors);
        model.addAttribute("actors", actors);
        return "add_movie";
    }

    @RequestMapping("/saveMovie")
    public String saveMovie(@ModelAttribute("movie") Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/";
    }

    @RequestMapping("/movies/{mid}")
    public String getMovie(@PathVariable("mid") Integer mid, Model model) {
        Optional<Movie> movie = movieService.getMovie(mid);
        movie.ifPresent(m -> model.addAttribute("movie", m));
        return "movie_details";
    }
}
