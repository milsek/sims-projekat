package com.example.library.service;

import com.example.library.model.Member;
import com.example.library.model.Reservation;
import com.example.library.repository.BookReservationRepository;
import com.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private BookReservationRepository bookReservationRepository;


    @Autowired
    private MemberRepository memberRepository;

    public List<Reservation> getReservationByMemberId(Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getReservations();
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
