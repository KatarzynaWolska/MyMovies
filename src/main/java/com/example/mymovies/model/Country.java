package com.example.mymovies.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="country")
public class Country {

    @Id
    @Column(name="country_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="country_name")
    private String name;

    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    private Set<Director> directors;

    @OneToMany(mappedBy="country")
    private Set<Actor> actors;

    public Country() {
        this.directors = new HashSet<>();
        this.actors = new HashSet<>();
    }

    @Override
    public String toString() {
        return name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Director> directors) {
        this.directors = directors;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
