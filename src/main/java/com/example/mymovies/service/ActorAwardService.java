package com.example.mymovies.service;

import com.example.mymovies.model.ActorAward;
import com.example.mymovies.repository.ActorAwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActorAwardService {

    @Autowired
    private ActorAwardRepository actorAwardRepo;

    public void addActorAward(ActorAward award) {
        actorAwardRepo.save(award);
    }
}
