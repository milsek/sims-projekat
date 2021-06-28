package com.example.library.model.reservation_states;

import com.example.library.model.ReservationState;

public class Denied extends ReservationState {
    private static Denied obj;

    private Denied() {
        super.setStateName(this.getClass().getName());
    };

    public static Denied getInstance(){
        if(obj == null){
            obj = new Denied();
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
