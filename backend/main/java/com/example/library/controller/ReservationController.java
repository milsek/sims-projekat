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

    @GetMapping(path = "/confirmReservation")
    @ResponseBody
    public Boolean isReservationPossibleByMemberId(@RequestParam(name = "id", required = false) Long id) {
        return reservationService.isReservationPossibleByMemberId(id);
    }
}
