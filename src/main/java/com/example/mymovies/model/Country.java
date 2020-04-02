package com.example.mymovies.model;

import javax.persistence.*;
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

    @OneToMany(mappedBy="country")
    private Set<Director> directors;

    @OneToMany(mappedBy="country")
    private Set<Actor> actors;
}
