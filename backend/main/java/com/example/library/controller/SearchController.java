package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Edition;
import com.example.library.model.Member;
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
    public Map<Long, List<Edition>> searchEditions(@RequestParam(name = "text", required = false) String text,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "amount") int amount) {
        if(text == null)
            text = "";
        return new HashMap<Long, List<Edition>>(editionService.searchEditions(text, page, amount));
    }

    @GetMapping(path = "/autocomplete_book_id/")
    @ResponseBody
    public Set<Book> autocompleteBookId(@RequestParam(name = "query") String id) {
        return bookService.autocompleteBookId(id);
    }

    @GetMapping(path = "/autocomplete_user_id/")
    @ResponseBody
    public List<Member> autocompleteUserId(@RequestParam(name = "query") String id) {
        return userService.autocompleteUserId(id);
    }
}
