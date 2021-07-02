package com.example.library.service;

import com.example.library.model.*;
import com.example.library.repository.BookReservationRepository;
import com.example.library.repository.MemberRepository;
import com.example.library.repository.ReservationRepository;
import com.example.library.repository.*;
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
    private EditionRepository editionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private BookRepository bookRepository;


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

    public Boolean reserveBook(long bookReservationId, long bookId){
        long userId = bookReservationRepository.findUserIdForReservation(bookReservationId);
        BookReservation bookReservation = bookReservationRepository.findById(bookReservationId).get();
        boolean possible = isReservationPossibleByMemberId(userId);
        Book book = bookRepository.findById(bookId).get();
        bookReservation.setBook(book);
        if(!possible){
            bookReservation.setReservationState(ReservationState.DENIED);
        }
        else{
            bookReservation.setReservationState(ReservationState.APPROVED);
            book.setBookState(BookState.RESERVED);
        }
        bookReservationRepository.save(bookReservation);
        return possible;
    }

    public Boolean reserveEdition(long userId, long editionId){
        Edition edition = editionRepository.findById(editionId).get();
        Member member = memberRepository.findById(userId).get();
        BookReservation bookReservation = new BookReservation(null,null,
                ReservationState.NEW,LocalDate.now(),null,edition);
        member.addReservation(bookReservation);
        bookReservationRepository.save(bookReservation);
        memberRepository.save(member);
        return true;
    }

    public Boolean isReservationPossibleByMemberId(Long id){
        long booksTakenOrReserved = reservationRepository.countBooksTakenOrReservedByUserId(id);
        Membership mem = membershipRepository.getMembershipIdByUserId(id, LocalDate.now().toString());
        if(mem == null){
            return false;
        }
        System.out.println(booksTakenOrReserved);
        CategoryRules categoryRules = mem.getPrice().getCategory();
        int allowedNumberOfBooks = categoryRules.getNumOfBooks();
        System.out.println(allowedNumberOfBooks);
        return booksTakenOrReserved < allowedNumberOfBooks;
    }
}

