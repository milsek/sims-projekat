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


    public List<Reservation> getReservationsByMemberId(Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getReservations();
    }

    public Boolean reservationReturned(Book book) {

        List<BookReservation> reservation = bookReservationRepository.findByReservationStateAndBookId(ReservationState.SEIZED.name(), book.getId());
        if ( reservation.size() != 1) {
            System.out.println("ERROR ERROR ERROR ERROR");
            return false;
        }
        reservation.get(0).setReservationState(ReservationState.RETURNED);
        reservation.get(0).setDateReturned(LocalDate.now());
        bookReservationRepository.save(reservation.get(0));
        return true;
    }

    public Boolean reserveBook(long bookReservationId, long bookId) {
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
        bookRepository.save(book);
        return possible;
    }

    public long reserveEdition(long userId, long editionId){
        Edition edition = editionRepository.findById(editionId).get();
        Member member = memberRepository.findById(userId).get();
        BookReservation bookReservation = new BookReservation(null,null,
                ReservationState.NEW,LocalDate.now(),null,edition);
        member.addReservation(bookReservation);
        bookReservationRepository.save(bookReservation);
        memberRepository.save(member);
        return bookReservation.getId();
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

    public Boolean takeBook(long userId, long bookId){
        if(!bookRepository.existsById(bookId) || !memberRepository.existsById(userId)) {
            return false;
        }
        Book book = bookRepository.findById(bookId).get();
        Long reservationId = reservationRepository.findByUserIdAndStateAndBookId(userId,"APPROVED", bookId);
        if(reservationId == null) {
            reservationId = reserveEdition(userId,book.getEdition().getId());
            boolean possible = reserveBook(reservationId,bookId);
            if(!possible){
                return false;
            }
        }
        BookReservation bookReservation = bookReservationRepository.findById(reservationId).get();
        bookReservation.setDateTaken(LocalDate.now());
        bookReservation.setReservationState(ReservationState.SEIZED);
        book.setBookState(BookState.TAKEN);
        bookRepository.save(book);
        bookReservationRepository.save(bookReservation);
        return true;
    }

    public BookReservation getReservationByMemberIdAndEditionId(Long memberId, Long editionId) {
        return bookReservationRepository.findByUser_IdAndEdition_Id(memberId, editionId);
    }
}

