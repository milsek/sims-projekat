package com.example.library.repository;

import com.example.library.model.BookReservation;
import com.example.library.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

    //public Set<BookReservation> getBookReservationBy

    public Map<Long, List<BookReservation>> searchReservations(String text, int page, int amount);
}
