package com.example.library.controller;


import com.example.library.model.Book;
import com.example.library.model.dto.BookChangePlacementDto;
import com.example.library.model.dto.SelectedBookDto;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/book/")
    @ResponseBody
    public SelectedBookDto getBookById(@RequestParam(name = "id", required = false) String id) {
        System.out.println(id);
        if (id.isEmpty()) return null;
        return bookService.findBookById(id);
    }

    @PostMapping("/change-placement")
    @ResponseBody
    public String changeBookPlacement(@RequestBody BookChangePlacementDto dto, @RequestParam Long bookId) {
        return bookService.changeBookPlacement(dto, bookId);
    }

}
