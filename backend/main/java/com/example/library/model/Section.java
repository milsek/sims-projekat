package com.example.library.model;


import javax.persistence.*;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
