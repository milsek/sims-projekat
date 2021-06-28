package com.example.library.model.reservation_states;

import com.example.library.model.ReservationState;

public class Returned extends ReservationState {
    private static Returned obj;

    private Returned() {
        super.setStateName(this.getClass().getName());
    };

    public static Returned getInstance(){
        if(obj == null){
            obj = new Returned();
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
