package com.example.library.model.dto;

public class ReviewSubmissionDto {

    private String content;

    private Double rating;

    private Long bookReservationMemberId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getBookReservationMemberId() {
        return bookReservationMemberId;
    }

    public void setBookReservationMemberId(Long bookReservationMemberId) {
        this.bookReservationMemberId = bookReservationMemberId;
    }
}
