package com.example.mymovies.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="movie")
public class Movie {

    @Id
    @Column(name="movie_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="title")
    private String title;

    @Column(name="length")
    private String length; //TODO jak przedstawiać długość

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToMany(mappedBy = "movies")
    private Set<MovieCategory> categories; // = new HashSet ???, private ???

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Movie_Actor",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )
    private Set<Actor> actors; // = new HashSet ???, private ???

    @OneToMany(mappedBy="movie")
    private Set<Award> awards;
}
