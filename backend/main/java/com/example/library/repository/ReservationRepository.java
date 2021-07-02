package com.example.library.repository;

import com.example.library.model.Reservation;
import com.example.library.model.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public Set<Reservation> findByReservationState(ReservationState reservationState);
}
