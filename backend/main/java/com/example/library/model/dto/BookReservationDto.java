package com.example.library.model.dto;

import java.time.LocalDate;

public class BookReservationDto {
    private Long userId;

    private String userName;

    private String userSurname;

    private String state;

    private String editionTitleTitle;

    private LocalDate dateTaken;

    private LocalDate dateReturned;

    private String editionImageLarge;

    private int bookLineNumber;

    private String bookLineIsleName;

    private Long bookId;

    private Long id;

    private Long editionId;

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

    public String getEditionImageLarge() {
        return editionImageLarge;
    }

    public void setEditionImageLarge(String editionImageLarge) {
        this.editionImageLarge = editionImageLarge;
    }

    public int getBookLineNumber() {
        return bookLineNumber;
    }

    public void setBookLineNumber(int bookLineNumber) {
        this.bookLineNumber = bookLineNumber;
    }

    public String getBookLineIsleName() {
        return bookLineIsleName;
    }

    public void setBookLineIsleName(String bookLineIsleName) {
        this.bookLineIsleName = bookLineIsleName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEditionId() {
        return editionId;
    }

    public void setEditionId(Long editionId) {
        this.editionId = editionId;
    }
}
