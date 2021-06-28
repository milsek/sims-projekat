package com.example.library.model.book_states;

import com.example.library.model.BookState;

public class Refurbishment extends BookState {
    private static Refurbishment obj;

    private Refurbishment() {
        super.setStateName(this.getClass().getName());
    };

    public static Refurbishment getInstance(){
        if(obj == null){
            obj = new Refurbishment();
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
