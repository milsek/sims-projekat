package com.example.library.model;

import com.example.library.model.book_states.InStock;
import com.example.library.model.book_states.Missing;
import com.example.library.model.reservation_states.New;
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

    public Book() {
        bookState = InStock.getInstance();
        bookState.setContext(this);
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
        try {
            Class<?> st = Class.forName(stateName);
            Method ins = st.getMethod("getInstance");
            bookState = (BookState) ins.invoke(ins);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
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
