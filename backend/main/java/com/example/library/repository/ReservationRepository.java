package com.example.library.repository;

import com.example.library.model.Reservation;
import com.example.library.model.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public Set<Reservation> findByReservationState(ReservationState reservationState);


    @Query(value = "select count(*) from reservation r where r.user_id = ?1 and (UPPER(state)='SEIZED' or UPPER(state)='RESERVED')", nativeQuery = true)
    public long countBooksTakenOrReservedByUserId(long userId);

    @Query(value = "select TOP 1 r.id from reservation r inner join book_reservation br on r.id=br.id where" +
            " r.user_id= ?1 and upper(r.state)= ?2 and br.book_id= ?3", nativeQuery = true)
    public Long findByUserIdAndStateAndBookId(long userId,String stateName,long bookId);
}