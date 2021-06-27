package com.example.library.model;

import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column
    private LocalDate dateTaken;

    @Column
    private LocalDate dateReturned;

    @Column
    private String stateName;

    private transient ReservationState reservationState;

    public void action() {
        reservationState = reservationState.action();
        stateName = reservationState.getStateName();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDate dateTaken) {
        this.dateTaken = dateTaken;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }
}
