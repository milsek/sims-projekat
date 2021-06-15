package com.example.library.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Indexed
public class Person {
    @Id
    @GeneratedValue
    protected long id;

    @Column
    @Field
    protected String name;

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
}
