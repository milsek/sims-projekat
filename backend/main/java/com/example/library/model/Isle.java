package com.example.library.model;

import javax.persistence.*;

@Entity
public class Isle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
