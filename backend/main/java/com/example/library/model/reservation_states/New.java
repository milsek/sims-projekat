package com.example.library.model.reservation_states;

import com.example.library.model.ReservationState;

public class New extends ReservationState {
    private static New obj;

    private New() {
        super.setStateName(this.getClass().getName());
    };

    public static New getInstance(){
        if(obj == null){
            obj = new New();
        }
        return obj;
    }
    public void entry(){

    };

    public void exit(){

    };

    public void doo(){
        System.out.println("Doodoo");
    };
}
