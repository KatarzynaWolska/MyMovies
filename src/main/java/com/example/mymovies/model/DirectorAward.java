package com.example.mymovies.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DirectorAward extends Award {

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
}
