package com.example.library.model.states;

import com.example.library.model.ReservationState;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;

@Entity
public class Approved extends ReservationState {

    public Approved() {
        name = this.getClass().getSimpleName();
    }
}
