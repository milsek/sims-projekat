package com.example.library.repository;

import com.example.library.model.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReservationRepo extends JpaRepository<BookReservation, Long> {
}
