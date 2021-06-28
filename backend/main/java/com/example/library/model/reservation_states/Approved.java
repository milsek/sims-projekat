package com.example.library.model.reservation_states;

import com.example.library.model.ReservationState;

public class Approved extends ReservationState {

    private static Approved obj;

    private Approved() {
        super.setStateName(this.getClass().getName());
    };

    public static Approved getInstance(){
        if(obj == null){
            obj = new Approved();
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
