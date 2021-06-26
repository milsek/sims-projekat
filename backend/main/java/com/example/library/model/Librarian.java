package com.example.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Librarian extends User {

    @Column
    private Workplace workplace;
}
