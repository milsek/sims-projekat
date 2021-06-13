package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SingleBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private BookStatus status;

    @ManyToOne
    private Edition edition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    @JsonIgnore
    public Edition getEdition() {
        return edition;
    }

    @JsonProperty("edition")
    public Long getEditionId() {
        return edition.getId();
    }
}
