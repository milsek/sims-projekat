package com.example.library.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int number;

    @ManyToOne
    @JoinColumn(name = "isle_id")
    private Isle isle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Isle getIsle() {
        return isle;
    }

    public void setIsle(Isle isle) {
        this.isle = isle;
    }
}
