package com.example.library.service;

import com.example.library.model.*;
import com.example.library.repository.BookReservationRepository;
import com.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        for(Reservation reservation : reservations){
            if(reservation.getReservationState() == ReservationState.APPROVED ||
                    reservation.getReservationState() == ReservationState.SEIZED){
                booksTakenOrReserved++;
            }
        }
        List<Membership> memberships = member.getMemberships();
        CategoryRules categoryRules = null;
        for(Membership mem : memberships) {
            if (mem.getEndDate().isAfter(LocalDate.now()) && mem.getStartDate().isBefore(LocalDate.now())) {
                categoryRules = mem.getPrice().getCategory();
            }
        }
        if(categoryRules == null){
            return false;
        }
        int allowedNumberOfBooks = categoryRules.getNumOfBooks();
        return booksTakenOrReserved < allowedNumberOfBooks;
    }
}
