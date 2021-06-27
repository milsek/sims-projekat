package com.example.library.model.states;

import com.example.library.model.ReservationState;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Seized extends ReservationState {
    private static Seized obj;

    private Seized(){};

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
