package com.example.library.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Member extends User {

    @OneToMany
    private List<Reservation> reservations;

}
