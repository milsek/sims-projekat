package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Indexed
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column
    @Field
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @JsonIgnore
//    public Set<Edition> getEditionSet() {
//        return editionSet;
//    }
//
//    @JsonProperty("editions")
//    public Set<Long> getEditionsId() {
//        Set<Long> set = new HashSet<>();
//        editionSet.stream().map(Edition::getId).forEachOrdered(set::add);
//        return set;
//    }
//
//    public void setEditionSet(Set<Edition> editionSet) {
//        this.editionSet = editionSet;
//    }
}
