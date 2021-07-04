package com.example.library.service;

import com.example.library.model.*;
import com.example.library.model.dto.BookReservationDto;
import com.example.library.model.dto.ReservationRequestDto;
import com.example.library.repository.BookReservationRepository;
import com.example.library.repository.MemberRepository;
import com.example.library.repository.ReservationRepository;
import com.example.library.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ModelMapper modelMapper;

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
                ReservationState.NEW, LocalDate.now(),null, edition);
        member.addReservation(bookReservation);
        bookReservation.setUser(member);
        bookReservationRepository.save(bookReservation);
        memberRepository.saveAndFlush(member);

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
            reservationId = reserveEdition(userId, book.getEdition().getId());
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

    public BookReservation getReservationByMemberIdAndEditionIdAndState(Long memberId, Long editionId, ReservationState state) {
        return bookReservationRepository.findByUser_IdAndEdition_IdAndReservationState(memberId, editionId, state);
    }

    public List<BookReservation> getReservationByMemberIdAndEditionId(Long memberId, Long editionId) {
        return bookReservationRepository.findByUser_IdAndEdition_Id(memberId, editionId);
    }

    public Boolean activeReservation(Long bookId) {
        Edition edition = bookRepository.findById(bookId).get().getEdition();
        List<BookReservation> bookReservations = bookReservationRepository.findByEditionId(edition.getId());
        for (BookReservation reservation: bookReservations) {
            if (reservation.getReservationState() == ReservationState.APPROVED) {
                return true;
            }
        }
        return false;
    }

    public List<ReservationRequestDto> reservationsRequest(String userId, String bookId, String bookTitle, String reservationState){
        return bookReservationRepository.findBookReservationByUserIdAndBookIdAndBookTitleAndState(userId, bookId, bookTitle, reservationState);
    }


    public Map<Long, List<BookReservationDto>> searchReservations(String userId, String bookId, String bookTitle, String status, int page, int amount) {
        Map<Long, List<BookReservation>> mapa = bookReservationRepository.searchReservations(userId, bookId, bookTitle, status, page, amount);
        List<BookReservation> lista = mapa.get(mapa.keySet().toArray()[0]);
        List<BookReservationDto> listaDto = lista.stream().map(x -> {return modelMapper.map(x, BookReservationDto.class);}).collect(Collectors.toList());
        Map<Long,List<BookReservationDto>> mapaDto = new HashMap<Long,List<BookReservationDto>>();
        mapaDto.put((Long)mapa.keySet().toArray()[0], listaDto);
        return mapaDto;
    }
}
