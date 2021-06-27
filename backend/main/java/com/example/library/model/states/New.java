package com.example.library.model.states;

import com.example.library.model.ReservationState;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class New extends ReservationState {
    private static New obj;

    private New(){};

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

    };
}
