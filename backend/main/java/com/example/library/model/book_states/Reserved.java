package com.example.library.model.book_states;

import com.example.library.model.BookState;

public class Reserved extends BookState {

    private static Reserved obj;

    private Reserved() {
        super.setStateName(this.getClass().getName());
    };

    public static Reserved getInstance(){
        if(obj == null){
            obj = new Reserved();
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
