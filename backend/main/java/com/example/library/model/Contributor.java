package com.example.library.model;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Contributor extends Person {
    @ManyToOne
    private ContributorType type;

    @ManyToMany(mappedBy = "contributors")
    private Set<Edition> editionSet;
}
