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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumOfPictureBooks() {
        return numOfPictureBooks;
    }

    public void setNumOfPictureBooks(int numOfPictureBooks) {
        this.numOfPictureBooks = numOfPictureBooks;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
