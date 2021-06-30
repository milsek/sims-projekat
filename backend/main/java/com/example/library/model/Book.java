package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BOOK_ID")
    private long id;

    @Column(name = "state")
    private String stateName;

    @Column
    private BookCondition condition;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Edition.class)
    @JoinColumn(name = "EDITION_ID")
    private Edition edition;

    @ManyToOne
    @JoinColumn(name = "LINE_ID")
    private Line line;

    private transient BookState bookState;

    @JsonProperty
    private transient String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book() {
        bookState = BookState.IN_STOCK;
        stateName = bookState.name();
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
        this.bookState = BookState.valueOf(stateName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStateName() {
        stateName = bookState.name();
        return stateName;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public void setCondition(BookCondition condition) {
        this.condition = condition;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
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
