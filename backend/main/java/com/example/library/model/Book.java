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

    @Column
    private BookCondition condition;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Edition.class)
    @JoinColumn(name = "EDITION_ID")
    private Edition edition;

    @ManyToOne
    @JoinColumn(name = "LINE_ID")
    private Line line;

    @Column(name = "state")
    @Enumerated(EnumType.ORDINAL)
    private BookState bookState;

    @JsonProperty
    private transient String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public BookState getBookState() {
        return bookState;
    }

    public void setBookState(BookState bookState) {
        this.bookState = bookState;
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
