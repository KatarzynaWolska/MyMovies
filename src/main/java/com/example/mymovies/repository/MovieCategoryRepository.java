package com.example.mymovies.repository;

import com.example.mymovies.model.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Integer> {
}
