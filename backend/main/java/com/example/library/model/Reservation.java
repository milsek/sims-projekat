package com.example.library.model;

import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @IndexedEmbedded(depth = 1)
    protected Long id;

    @Column
    @IndexedEmbedded(depth = 1)
    private LocalDate dateTaken;

    @Column
    @IndexedEmbedded(depth = 1)
    private LocalDate dateReturned;

    @Column(name = "state")
    @IndexedEmbedded(depth = 1)
    private String stateName;

    private transient ReservationState reservationState;

    public Reservation(Long id, LocalDate dateTaken, LocalDate dateReturned, String stateName) {
        this.id = id;
        this.dateTaken = dateTaken;
        this.dateReturned = dateReturned;
        this.stateName = stateName;
    }

    public Reservation() {
        reservationState = ReservationState.NEW;
    }

    public String getStateName() {
        stateName = reservationState.name();
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
        this.reservationState = ReservationState.valueOf(stateName);
    }


    public ReservationState getReservationState() {
        return reservationState;
    }

    public void setReservationState(ReservationState reservationState) {
        this.reservationState = reservationState;
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
