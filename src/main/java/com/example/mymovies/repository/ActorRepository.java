package com.example.mymovies.repository;

import com.example.mymovies.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
