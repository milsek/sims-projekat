package com.example.library.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Contributor extends Person {
    @ManyToOne
    private ContributorType type;

    @OneToMany(mappedBy = "contributor")
    private Set<Edition> editionSet;
}
