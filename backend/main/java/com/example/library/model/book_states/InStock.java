package com.example.library.model.book_states;

import com.example.library.model.BookState;

public class InStock extends BookState {

    private static InStock obj;

    private InStock() {
        super.setStateName(this.getClass().getName());
    };

    public static InStock getInstance(){
        if(obj == null){
            obj = new InStock();
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
