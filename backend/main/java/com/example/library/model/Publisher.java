package com.example.library.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<Edition> editionSet;
}
