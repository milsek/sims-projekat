package com.example.library.model.states;

import com.example.library.model.ReservationState;
import org.springframework.stereotype.Component;

@Component
public class New extends ReservationState {

    public New() {
        stateName = this.getClass().getSimpleName();
    }
}
