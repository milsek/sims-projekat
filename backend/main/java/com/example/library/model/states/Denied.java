package com.example.library.model.states;

import com.example.library.model.ReservationState;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Denied extends ReservationState {
    private static Denied obj;

    private Denied() {
        super.setStateName(this.getClass().getSimpleName());
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
