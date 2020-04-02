package com.example.mymovies.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="actor")
public class Actor {

    @Id
    @Column(name="actor_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="actor_name")
    private String name;

    @Column(name="actor_surname")
    private String surname;

    @Column(name="actor_birth_date")
    private Date date_of_birth;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies; // = new HashSet ???

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy="actor")
    private Set<ActorAward> awards;
}
