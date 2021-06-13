package com.example.library.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BookType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private int availableCopies;

    @Column
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
