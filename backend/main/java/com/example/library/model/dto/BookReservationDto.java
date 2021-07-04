package com.example.library.model.dto;

import java.time.LocalDate;

public class BookReservationDto {
    private Long userId;

    private String userName;

    private String userSurname;

    private String reservationState;

    private String editionTitleTitle;

    private LocalDate dateTaken;

    private LocalDate dateReturned;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    public String getEditionTitleTitle() {
        return editionTitleTitle;
    }

    public void setEditionTitleTitle(String editionTitleTitle) {
        this.editionTitleTitle = editionTitleTitle;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDate dateTaken) {
        this.dateTaken = dateTaken;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }
}
