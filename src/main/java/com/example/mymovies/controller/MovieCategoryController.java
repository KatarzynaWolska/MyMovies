package com.example.mymovies.controller;

import com.example.mymovies.model.MovieCategory;
import com.example.mymovies.service.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieCategoryController {

    @Autowired
    private MovieCategoryService categoryService;

    @RequestMapping("/addMovieCategory")
    public String addMovieCategory(Model model) {
        MovieCategory movieCategory = new MovieCategory();
        model.addAttribute("category", movieCategory);
        return "add_movie_category";
    }

    @RequestMapping("/movieCategories")
    public String getMovieCategories(Model model) {
        List<MovieCategory> categories = categoryService.getMoviesCategories();
        model.addAttribute("categories", categories);
        return "movie_categories";
    }

    @RequestMapping("/saveMovieCategory")
    public String saveMovieCategory(@ModelAttribute("category") MovieCategory category) {
        categoryService.addCategory(category);
        return "redirect:/";
    }

    @RequestMapping("/movieCategories/{mid}")
    public String getMovieCategory(@PathVariable("mid") Integer mid, Model model) {
        Optional<MovieCategory> category = categoryService.getMovieCategory(mid);
        category.ifPresent(c -> model.addAttribute("category", c));
        category.ifPresent(c -> model.addAttribute("movies", c.getMovies()));
        return "movie_category_details";
    }

    @RequestMapping("/deleteMovieCategory/{cid}")
    public String deleteMovieCategory(@PathVariable("cid") Integer cid) {
        Optional<MovieCategory> movieCategory = categoryService.getMovieCategory(cid);
        movieCategory.ifPresent(category -> category.getMovies().forEach(movie -> movie.setCategories(null)));
        categoryService.deleteMovieCategory(cid);
        return "redirect:/movieCategories";
    }

    @RequestMapping("/editMovieCategory/{mid}")
    public String editMovieCategory(@PathVariable("mid") Integer mid, Model model) {
        Optional<MovieCategory> category = categoryService.getMovieCategory(mid);
        category.ifPresent(c -> model.addAttribute("category", c));
        return "edit_movie_category";
    }
}
