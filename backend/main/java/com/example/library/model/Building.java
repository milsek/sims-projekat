package com.example.library.model;

import javax.persistence.*;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private int numOfPictureBooks;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Place.class)
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
