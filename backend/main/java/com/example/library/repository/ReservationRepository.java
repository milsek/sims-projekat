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
}
