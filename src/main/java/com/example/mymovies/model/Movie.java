package com.example.mymovies.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="movie")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Movie {

    @Id
    @Column(name="movie_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="length")
    private String length; //TODO jak przedstawiać długość

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private Director director;

    @Column(name = "is_favourite", columnDefinition = "boolean default false")
    private Boolean isFav;

    @Column(name = "watched", columnDefinition = "boolean default false")
    private Boolean watched;

    @Column(name ="rating")
    private Integer rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Category_Movie",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "movie_category_id") }
    )
    private Set<MovieCategory> categories; // = new HashSet ???, private ???

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Movie_Actor",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )
    private Set<Actor> actors; // = new HashSet ???, private ???

    @OneToMany(mappedBy="movie")
    private Set<Award> awards;

    public Movie() {
        this.actors = new HashSet<>();
        this.categories = new HashSet<>();
        this.awards = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<MovieCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<MovieCategory> categories) {
        this.categories = categories;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public Boolean isFav() {
        return isFav;
    }

    public void setFav(Boolean fav) {
        isFav = fav;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }
}
