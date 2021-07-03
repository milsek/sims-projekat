package com.example.library.model.dto;

public interface ReservationRequestDto {
    Long getId();
    String getTitle();
    String getState();
    String getImageLarge();
    String getIsleName();
    Integer getLineNumber();
    Long getBookReservationId();
    Long getUserId();
    String getName();
    String getSurname();
}
