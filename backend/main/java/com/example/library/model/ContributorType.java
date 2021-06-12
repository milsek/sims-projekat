package com.example.library.model;

import javax.persistence.*;

@Entity
@Table
public class ContributorType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String role;

}
