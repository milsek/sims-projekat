package com.example.library.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column
    private String name;

    @ManyToMany(mappedBy = "contents")
    private Set<Edition> editionSet;


}
