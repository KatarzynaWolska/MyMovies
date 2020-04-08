package com.example.mymovies.repository;

import com.example.mymovies.model.Award;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MovieAwardRepository extends AwardBaseRepository<Award>{
}
