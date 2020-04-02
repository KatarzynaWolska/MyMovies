package com.example.mymovies.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ActorAward extends Award {

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;
}
