package com.example.library.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Contributor extends Person {
    @ManyToOne
    private ContributorType type;

    @ManyToMany(mappedBy = "contributors")
    private Set<Edition> editionSet;

    public ContributorType getType() {
        return type;
    }

    public void setType(ContributorType type) {
        this.type = type;
    }

    @JsonIgnore
    public void setEditionSet(Set<Edition> editionSet) {
        this.editionSet = editionSet;
    }

    @JsonProperty("editions")
    public Set<Long> getEditionsId() {
        Set<Long> set = new HashSet<>();
        editionSet.stream().map(Edition::getId).forEachOrdered(set::add);
        return set;
    }
}
