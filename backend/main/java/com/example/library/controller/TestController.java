package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.model.Membership;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BookReservationRepository;
import com.example.library.repository.MembershipRepository;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookReservationRepository reservationRepo;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @GetMapping(path = "/hello")
    @ResponseBody
    public String hello(@RequestParam Long id) {
        Membership membership = membershipRepository.findById(id).get();
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
        return libraryService.getLibraryById(id);
    }

}
