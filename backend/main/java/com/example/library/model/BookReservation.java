package com.example.library.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BookReservation extends Reservation {

    @Column
    private LocalDate reservationDate;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Book.class)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
