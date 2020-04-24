package com.example.mymovies.controller;

import com.example.mymovies.model.MovieCategory;
import com.example.mymovies.service.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
