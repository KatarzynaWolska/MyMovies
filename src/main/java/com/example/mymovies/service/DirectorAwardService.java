package com.example.mymovies.service;

import com.example.mymovies.model.DirectorAward;
import com.example.mymovies.repository.DirectorAwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DirectorAwardService {

    @Autowired
    private DirectorAwardRepository directorAwardRepo;

    public void addDirectorAward(DirectorAward award) {
        directorAwardRepo.save(award);
    }
}
