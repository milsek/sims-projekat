package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.BookReservation;
import com.example.library.model.Library;
import com.example.library.model.Reservation;
import com.example.library.repository.BookReservationRepo;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookReservationRepo reservationRepo;

    @GetMapping(path = "/hello")
    @ResponseBody
    public String hello() {
        BookReservation br = reservationRepo.findById(1L).get();
        br.action();
        return "Hello:)";
    }

    @PostMapping(path = "/addLibrary")
    @ResponseBody
    public void addLibrary(@RequestBody Library library) {
        libraryService.insertLibrary(library);
    }

    @GetMapping(path = "/getLibrary")
    @ResponseBody
    public Library getLibrary(@RequestBody long id) {
        return libraryService.findLibrary(id);
    }

}
