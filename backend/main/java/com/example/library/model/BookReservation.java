package com.example.library.model;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Indexed
public class BookReservation extends Reservation {

    @Column
    private LocalDate reservationDate;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Book.class)
    @JoinColumn(name = "BOOK_ID")
    @IndexedEmbedded(depth = 1)
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
