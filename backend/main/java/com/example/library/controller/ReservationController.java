package com.example.library.controller;


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

    @GetMapping(path = "/confirm-reservation")
    @ResponseBody
    public Boolean isReservationPossibleByMemberId(@RequestParam(name = "id", required = false) Long id) {
        return reservationService.isReservationPossibleByMemberId(id);
    }

    @GetMapping(path = "/reserve-book")
    @ResponseBody
    public Boolean reserveBook(@RequestParam(name = "bookReservationId", required = false) Long bookReservationId,
                               @RequestParam(name = "bookId", required = false) Long bookId) {
        return reservationService.reserveBook(bookReservationId, bookId);
    }

    @GetMapping(path = "/reserve-edition")
    @ResponseBody
    public Boolean reserveEdition(@RequestParam(name = "userId", required = false) Long userId,
                                  @RequestParam(name = "editionId", required = false) Long editionId) {
        return reservationService.reserveEdition(userId,editionId);
    }
}
