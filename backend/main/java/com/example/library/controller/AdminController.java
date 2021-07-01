package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/get_book/")
    @ResponseBody
    public Book getBookById(@RequestParam(name = "query", required = false) String id) {
        System.out.println(id);
        if (id.isEmpty()) return null;
        return bookService.findBookById(id);
    }

}
