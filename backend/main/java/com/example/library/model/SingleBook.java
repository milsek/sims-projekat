package com.example.library.model;

import javax.persistence.*;

@Entity
public class SingleBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private BookStatus status;

    @ManyToOne
    private Edition edition;
}
