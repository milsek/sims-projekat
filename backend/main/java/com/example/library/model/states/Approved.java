package com.example.library.model.states;

import com.example.library.model.ReservationState;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Approved extends ReservationState {

    private static Approved obj;

    private Approved(){};

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
