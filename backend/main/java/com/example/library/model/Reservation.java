package com.example.library.model;

import com.example.library.config.ReservationStateConverter;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Locale;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @IndexedEmbedded(depth = 1)
    protected Long id;

    @Column
    @IndexedEmbedded(depth = 1)
    private LocalDate dateTaken;

    @Column
    @IndexedEmbedded(depth = 1)
    private LocalDate dateReturned;

    @Column(name = "state")
    @Convert(converter = ReservationStateConverter.class)
    protected ReservationState reservationState;

    public Reservation(Long id, LocalDate dateTaken, LocalDate dateReturned, ReservationState reservationState) {
        this.id = id;
        this.dateTaken = dateTaken;
        this.dateReturned = dateReturned;
        this.reservationState = reservationState;
    }

    public Reservation(LocalDate dateTaken, LocalDate dateReturned, ReservationState reservationState) {
        this.dateTaken = dateTaken;
        this.dateReturned = dateReturned;
        this.reservationState = reservationState;
    }

    public Reservation() {

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
