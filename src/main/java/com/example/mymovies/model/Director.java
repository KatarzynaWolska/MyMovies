package com.example.mymovies.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="director")
public class Director {

    @Id
    @Column(name="director_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="director_name")
    private String name;

    @Column(name="director_surname")
    private String surname;

    @Column(name="director_birth_date")
    private Date date_of_birth;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy="director")
    private Set<Movie> movies;

    @OneToMany(mappedBy="director")
    private Set<DirectorAward> awards;
}
