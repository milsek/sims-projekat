package com.example.library.model.states;

import com.example.library.model.ReservationState;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Returned extends ReservationState {
    private static Returned obj;

    private Returned() {
        super.setStateName(this.getClass().getSimpleName());
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
