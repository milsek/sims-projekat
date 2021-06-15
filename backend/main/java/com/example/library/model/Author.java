package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
public class Author extends Person {
    @OneToMany(mappedBy = "author")
    private Set<Edition> editionSet;

    public void setEditionSet(Set<Edition> editionSet) {
        this.editionSet = editionSet;
    }

    @JsonIgnore
    public Set<Edition> getEditionSet() {
        return editionSet;
    }

    @JsonProperty("editions")
    public Set<Long> getEditionsId() {
        Set<Long> set = new HashSet<>();
        editionSet.stream().map(Edition::getId).forEachOrdered(set::add);
        return set;
    }
}
