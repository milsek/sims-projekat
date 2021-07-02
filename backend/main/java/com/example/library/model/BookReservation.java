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

    @ManyToOne
    @JoinColumn(name = "EDITION_ID")
    @IndexedEmbedded(depth = 1)
    private Edition edition;

    @OneToOne
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    public BookReservation() {
        super();
    }

    public BookReservation(LocalDate dateTaken, LocalDate dateReturned, ReservationState reservationState, LocalDate reservationDate, Book book, Edition edition) {
        super(dateTaken, dateReturned, reservationState);
        this.reservationDate = reservationDate;
        this.book = book;
        this.edition = edition;
    }

    public BookReservation(long id, LocalDate dateTaken, LocalDate dateReturned, ReservationState reservationState, LocalDate reservationDate, Book book, Edition edition) {
        super(id, dateTaken, dateReturned, reservationState);
        this.reservationDate = reservationDate;
        this.book = book;
        this.edition = edition;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

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

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
