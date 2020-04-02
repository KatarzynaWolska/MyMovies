package com.example.mymovies.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="movie_category")
public class MovieCategory {

    @Id
    @Column(name="movie_category_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="movie_category_name")
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Category_Movie",
            joinColumns = { @JoinColumn(name = "movie_category_id") },
            inverseJoinColumns = { @JoinColumn(name = "movie_id") }
    )
    private Set<Movie> movies; // = new HashSet ???
}
