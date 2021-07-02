package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Edition;
import com.example.library.model.Member;
import com.example.library.model.dto.SearchEditionDto;
import com.example.library.service.BookService;
import com.example.library.service.BookTitleService;
import com.example.library.service.EditionService;
import com.example.library.service.UserService;
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
    public Set<Book> autocompleteBookId(@RequestParam(name = "id") String id) {
        return bookService.autocompleteBookId(id);
    }

    @GetMapping(path = "/autocomplete-user-id/")
    @ResponseBody
    public List<Member> autocompleteUserId(@RequestParam(name = "id") String id) {
        return userService.autocompleteUserId(id);
    }
}
