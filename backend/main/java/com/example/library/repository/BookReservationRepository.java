package com.example.library.repository;

import com.example.library.model.BookReservation;
import com.example.library.model.Edition;
import com.example.library.model.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

    public List<BookReservation> getBookReservationsByBook_IdAndReservationState(long book_id, ReservationState reservationState);

    public List<BookReservation> findBookReservationByReservationStateAndBook_Id(ReservationState reservationState, long book_id);

    public List<BookReservation> findBookReservationsByBook_Id(long book_id);

    @Query(value = "select br.*, r.* from book_reservation as br join reservation as r on br.id = r.id where r.state = ?1 and br.book_id = ?2", nativeQuery = true)
    public List<BookReservation> findByReservationStateAndBookId(String reservationState, Long book_id);

    @Query(value = "select r.user_id from reservation r where r.id=?1", nativeQuery = true)
    public Long findUserIdForReservation(long reservationId);

    public Map<Long, List<BookReservation>> searchReservations(String text, int page, int amount);

    public BookReservation findByUser_IdAndEdition_Id(Long userId, Long editionId);
}
