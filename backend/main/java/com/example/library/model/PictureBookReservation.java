package com.example.library.model;

import javax.persistence.*;

@Entity
public class PictureBookReservation extends Reservation {

    @Column
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
