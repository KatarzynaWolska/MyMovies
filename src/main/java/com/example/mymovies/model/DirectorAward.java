package com.example.mymovies.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DirectorAward extends Award {

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    public DirectorAward() {
        super();
    }

    public DirectorAward(Director director) {
        super();
        this.director = director;
    }

    public DirectorAward(String name, Movie movie, Director director) {
        super(name, movie);
        this.director = director;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
