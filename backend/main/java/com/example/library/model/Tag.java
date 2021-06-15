package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Set;

@Entity
@Indexed
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @Field
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonView(Tag.class)
    private Set<Edition> editionSet;

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

    public void setEditionSet(Set<Edition> editionSet) {
        this.editionSet = editionSet;
    }

    public Set<Edition> getEditionSet() {
        return editionSet;
    }
}
