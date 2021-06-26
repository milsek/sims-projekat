package com.example.library.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DailyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "LIBRARIAN_ID")
    private Librarian librarian;

    @OneToOne
    private Membership membership;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
