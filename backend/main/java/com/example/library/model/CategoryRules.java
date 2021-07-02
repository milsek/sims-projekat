package com.example.library.model;

import javax.persistence.*;

@Entity
public class CategoryRules {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private int numOfDays;

    @Column
    private int numOfBooks;

    @Column
    private MemberType category;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public int getNumOfBooks() {
        return numOfBooks;
    }

    public void setNumOfBooks(int numOfBooks) {
        this.numOfBooks = numOfBooks;
    }

    public MemberType getCategory() {
        return category;
    }

    public void setCategory(MemberType category) {
        this.category = category;
    }
}
