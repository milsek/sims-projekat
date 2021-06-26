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
}
