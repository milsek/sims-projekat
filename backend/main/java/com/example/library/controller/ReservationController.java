package com.example.library.controller;


import com.example.library.model.dto.ReservationRequestDto;
import com.example.library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping(path = "/confirm-reservation")
    @ResponseBody
    public Boolean isReservationPossibleByMemberId(@RequestParam(name = "id", required = false) Long id) {
        return reservationService.isReservationPossibleByMemberId(id);
    }

    @PostMapping(path = "/reserve-book")
    @ResponseBody
    public Boolean reserveBook(@RequestParam(name = "bookReservationId", required = false) Long bookReservationId,
                               @RequestParam(name = "bookId", required = false) Long bookId) {
        return reservationService.reserveBook(bookReservationId, bookId);
    }

    @PostMapping(path = "/reserve-edition")
    @ResponseBody
    public long reserveEdition(@RequestParam(name = "userId", required = false) Long userId,
                                  @RequestParam(name = "editionId", required = false) Long editionId) {
        return reservationService.reserveEdition(userId,editionId);
    }

    @PostMapping(path = "/take-book")
    @ResponseBody
    public Boolean takeBook(@RequestParam(name = "userId", required = false) Long userId,
                         @RequestParam(name = "bookId", required = false) Long bookId) {
        return reservationService.takeBook(userId, bookId);
    }

    @PostMapping(path = "/return-book/")
    @ResponseBody
    public String returnBook(@RequestParam(name = "id") Long reservationId) {
        return reservationService.returnBook(reservationId);
    }

    @GetMapping(path = "/active-reservation")
    @ResponseBody
    public Boolean activeReservation(@RequestParam(name = "bookId") String bookId) {
        try {
            Long.valueOf(bookId);
        } catch (Exception e) {
            return false;
        }
        return reservationService.activeReservation(Long.valueOf(bookId));
    }

    //String userId, String bookId, String bookTitle, String reservationState
    @PostMapping(path = "/request-reservation")
    @ResponseBody
    public List<ReservationRequestDto> reservationsRequest(@RequestParam(name = "userId", required = false) String userId,
                                                           @RequestParam(name = "bookId", required = false) String bookId,
                                                           @RequestParam(name = "bookTitle", required = false) String bookTitle,
                                                           @RequestParam(name = "reservationState", required = false) String reservationState) {
        return reservationService.reservationsRequest(userId, bookId, bookTitle, reservationState);
    }


    @GetMapping(path = "/can-user-reserve")
    @ResponseBody
    public Boolean canUserReserveEdition(@RequestParam(name = "userId") String userId,
                                         @RequestParam(name = "editionId") String editionId) {
        return reservationService.canUserReserveEdition(userId, editionId);
    }

}
