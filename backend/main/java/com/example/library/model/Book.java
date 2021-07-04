package com.example.library.model;

import com.example.library.config.BookConditionConverter;
import com.example.library.config.BookStateConverter;
import com.example.library.config.ReservationStateConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.bridge.builtin.LongBridge;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "book_id")
    @FieldBridge(impl = LongBridge.class)
    @Column(name = "BOOK_ID")
    private Long id;

    public Book() {
    }

    public Book(long id, BookCondition condition, Edition edition, Line line, BookState bookState, String title) {
        this.id = id;
        this.condition = condition;
        this.edition = edition;
        this.line = line;
        this.bookState = bookState;
        this.title = title;
    }

    @Column
    @Convert(converter = BookConditionConverter.class)
    private BookCondition condition;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Edition.class)
    @JoinColumn(name = "EDITION_ID")
    private Edition edition;

    @ManyToOne
    @JoinColumn(name = "LINE_ID")
    private Line line;

    @Column(name = "state")
    @Convert(converter = BookStateConverter.class)
    private BookState bookState;

    @JsonProperty
    private transient String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
