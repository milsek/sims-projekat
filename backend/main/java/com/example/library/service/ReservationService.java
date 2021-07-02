package com.example.library.service;

import com.example.library.model.*;
import com.example.library.repository.BookReservationRepository;
import com.example.library.repository.MemberRepository;
import com.example.library.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    private BookReservationRepository bookReservationRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Reservation> getReservationByMemberId(Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getReservations();
    }

    public void reservationReturned(Book book) {

        List<BookReservation> reservation = bookReservationRepository.findByReservationStateAndBookId(ReservationState.SEIZED.name().toLowerCase(), book.getId());
        if ( reservation.size() != 1) {
            System.out.println("ERROR ERROR ERROR ERROR");
            return;
        }
        reservation.get(0).setReservationState(ReservationState.RETURNED);
        reservation.get(0).setDateReturned(LocalDate.now());
        bookReservationRepository.save(reservation.get(0));
    }

    public Boolean isReservationPossibleByMemberId(Long id){
        int booksTakenOrReserved = 0;
        Member member = memberRepository.findById(id).get();
        List<Reservation> reservations = member.getReservations();
        System.out.println(reservations.size()+"AAAAAAAAAAAAA");
        for(Reservation reservation : reservations){
            System.out.println(reservation.getReservationState().name());
            if(reservation.getReservationState().name().equals("APPROVED") ||
                    reservation.getReservationState().name().equals("SEIZED")){
                booksTakenOrReserved++;
            }
        }

        return booksTakenOrReserved > 2;
    }
}
