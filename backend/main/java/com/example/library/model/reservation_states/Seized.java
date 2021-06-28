package com.example.library.model.reservation_states;

import com.example.library.model.ReservationState;

public class Seized extends ReservationState {
    private static Seized obj;

    private Seized() {
        super.setStateName(this.getClass().getName());
    };

    public static Seized getInstance(){
        if(obj == null){
            obj = new Seized();
        }
        return obj;
    }
    public void entry(){

    };

    public void exit(){

    };

    public void doo(){

    };
}
