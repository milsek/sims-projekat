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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
