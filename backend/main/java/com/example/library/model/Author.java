package com.example.library.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Author extends Person {
    @OneToMany(mappedBy = "author")
    private Set<Edition> editionSet;
}
