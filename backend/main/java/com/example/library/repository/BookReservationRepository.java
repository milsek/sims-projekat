package com.example.library.repository;

import com.example.library.model.BookReservation;
import com.example.library.model.ReservationState;
import com.example.library.model.dto.ReservationRequestDto;
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

    public BookReservation findByUser_IdAndEdition_IdAndReservationState(Long memberId, Long editionId, ReservationState state);

    public List<BookReservation> findByEditionId(Long id);

    @Query(value = "select b.book_id as id, bt.title as title, r.state, ed.image_large as imageLarge,line.number as lineNumber,isle.name as isleName from book_reservation as br join reservation as r on br.id = r.id " +
            "inner join book_title bt on br.edition_id=bt.id inner join edition ed on br.edition_id=ed.id " +
            "inner join book b on b.book_id=br.book_id inner join line on b.line_id=line.id inner join isle on isle.id=line.isle_id where " +
            "(user_Id=?1 or ?1 is null) and " +
            "(b.book_Id=?2 or ?2 is null) and " +
            "(title=?3 or ?3 is null) and " +
            "(r.state=?4 or ?4 is null)", nativeQuery = true)
    public List<ReservationRequestDto> findBookReservationByUserIdAndBookIdAndBookTitleAndState(String userId, String bookId, String bookTitle, String reservationState);

}
