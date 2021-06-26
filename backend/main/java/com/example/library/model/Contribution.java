package com.example.library.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
public class Contribution {
    @Id
    @GeneratedValue
    protected long id;

    @ManyToOne
    private Contributor contributor;

    @Column
    private String contributorType;
}
