package com.example.library.model;

import com.example.library.model.reservation_states.*;

import javax.persistence.*;
import java.lang.reflect.Method;
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

    public Reservation(Long id, LocalDate dateTaken, LocalDate dateReturned, String stateName) {
        System.out.println("Konstruktor 1");
        this.id = id;
        this.dateTaken = dateTaken;
        this.dateReturned = dateReturned;
        this.stateName = stateName;
    }

    public Reservation() {
        System.out.println("Konstruktor 2");
        reservationState = New.getInstance();
        reservationState.setContext(this);
    }

    public void action() {
        reservationState.doo();
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
        try {
            Class<?> st = Class.forName(stateName);
            Method ins = st.getMethod("getInstance");
            reservationState = (ReservationState) ins.invoke(ins);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
