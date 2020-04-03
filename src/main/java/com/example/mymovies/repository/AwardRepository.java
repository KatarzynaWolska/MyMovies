package com.example.mymovies.repository;

import com.example.mymovies.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardRepository extends JpaRepository<Award, Integer> {
}
