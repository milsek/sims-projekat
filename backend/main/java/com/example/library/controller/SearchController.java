package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Edition;
import com.example.library.model.Member;
import com.example.library.model.dto.*;
import com.example.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    @Autowired
    private EditionService editionService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookTitleService bookTitleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping(path = "fullSearch")
    @ResponseBody
    public SearchEditionDto searchEditions(@RequestParam(name = "text", required = false) String text,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "amount") int amount) {
        if(text == null)
            text = "";
        return editionService.searchEditions(text, page, amount);
    }

    @GetMapping(path = "/autocomplete-book-id/")
    @ResponseBody
    public Set<AutocompleteBookDto> autocompleteBookId(@RequestParam(name = "id") String id) {
        return bookService.autocompleteBookId(id);
    }

    @GetMapping(path = "/autocomplete-user-id/")
    @ResponseBody
    public List<AutocompleteUserDto> autocompleteUserId(@RequestParam(name = "id") String id) {
        return userService.autocompleteUserId(id);
    }

    @GetMapping(path = "/genre/")
    @ResponseBody
    private Set<Edition> searchByGenre(@RequestParam(name = "id") String id) {
        return bookTitleService.getEditionsByGenreId(Long.valueOf(id));
    }

    @GetMapping("search-book")
    @ResponseBody
    public List<SelectedBookDto> searchBook(
            @RequestParam(name = "bookId", required = false) String id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "status", required = false) String status) {
        return bookService.searchBook(id, title, author, status);
    }

    @GetMapping(path = "search-reservations")
    @ResponseBody
    public Map<Long,List<BookReservationDto>> searchReservations(@RequestParam(name = "userId", required = false) String userId,
                                                                 @RequestParam(name = "bookId", required = false) String bookId,
                                                                 @RequestParam(name = "bookTitle", required = false) String bookTitle,
                                                                 @RequestParam(name = "status", required = false) String status,
                                                                 @RequestParam(name = "page") int page,
                                                                 @RequestParam(name = "amount") int amount) {
        return reservationService.searchReservations(userId, bookId, bookTitle, status, page, amount);
    }

}
