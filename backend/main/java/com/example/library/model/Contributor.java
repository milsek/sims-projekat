package com.example.library.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Contributor extends Person {

    @ManyToOne
    private ContributorType type;
}
