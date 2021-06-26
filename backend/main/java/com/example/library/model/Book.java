package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BOOK_ID")
    private long id;

    @Column
    private BookStatus status;

    @Column
    private BookCondition condition;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Edition.class)
    @JoinColumn(name = "EDITION_ID")
    private Edition edition;

    public Book() {
    }


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

    public BookCondition getCondition() {
        return condition;
    }

    public void setCondition(BookCondition condition) {
        this.condition = condition;
    }

    //    public void setEdition(Edition edition) {
//        this.edition = edition;
//    }
//
//    @JsonIgnore
//    public Edition getEdition() {
//        return edition;
//    }
//
//    @JsonProperty("edition")
//    public Long getEditionId() {
//        return edition.getId();
//    }
}
