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
}
