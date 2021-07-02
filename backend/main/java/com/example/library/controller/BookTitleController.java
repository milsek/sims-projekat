package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.service.BookTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class BookTitleController {

    @Autowired
    private BookTitleService bookTitleService;

}
