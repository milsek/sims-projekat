package com.example.library.model.book_states;

import com.example.library.model.BookState;

public class Taken extends BookState {
    private static Taken obj;

    private Taken() {
        super.setStateName(this.getClass().getName());
    };

    public static Taken getInstance(){
        if(obj == null){
            obj = new Taken();
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
