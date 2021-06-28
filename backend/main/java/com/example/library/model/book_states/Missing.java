package com.example.library.model.book_states;

import com.example.library.model.BookState;
import com.example.library.model.reservation_states.New;

public class Missing extends BookState {
    private static Missing obj;

    private Missing() {
        super.setStateName(this.getClass().getName());
    };

    public static Missing getInstance(){
        if(obj == null){
            obj = new Missing();
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
