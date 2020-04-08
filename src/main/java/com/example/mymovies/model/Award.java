package com.example.mymovies.model;

import javax.persistence.*;

@Entity
@Table(name="award")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Award {

    @Id
    @Column(name="award_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="award_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public Award() {

    }

    public Award(String name, Movie movie) {
        this.name = name;
        this.movie = movie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
