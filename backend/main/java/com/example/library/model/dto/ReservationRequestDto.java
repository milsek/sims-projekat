package com.example.library.model.dto;

import com.example.library.model.ReservationState;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class ReservationRequestDto {
    private Long id;
    private String bookTitle;
    private String reservationState;
    private int row;
    private String isle;
    private String imageLarge;

    public ReservationRequestDto() {
    }

    public ReservationRequestDto(Long id, String bookTitle, String reservationState, int row, String isle, String imageLarge) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.reservationState = reservationState;
        this.row = row;
        this.isle = isle;
        this.imageLarge = imageLarge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }
}
