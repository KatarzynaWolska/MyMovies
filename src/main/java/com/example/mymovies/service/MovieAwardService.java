package com.example.mymovies.service;

import com.example.mymovies.model.Award;
import com.example.mymovies.repository.MovieAwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieAwardService {

    @Autowired
    private MovieAwardRepository movieAwardRepo;

    public void addMovieAward(Award award) {
        movieAwardRepo.save(award);
    }
}
