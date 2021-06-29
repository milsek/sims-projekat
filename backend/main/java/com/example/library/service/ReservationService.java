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
    private BookReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Reservation> getReservationByMemberId(Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getReservations();
    }
}
