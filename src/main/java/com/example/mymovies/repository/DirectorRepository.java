package com.example.mymovies.repository;

import com.example.mymovies.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
