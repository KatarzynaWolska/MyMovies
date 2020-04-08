package com.example.mymovies.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ActorAward extends Award {

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    public ActorAward() {
        super();
    }

    public ActorAward(Actor actor) {
        super();
        this.actor = actor;
    }

    public ActorAward(String name, Movie movie, Actor actor) {
        super(name, movie);
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
