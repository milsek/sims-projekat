package com.example.library.controller;

import com.example.library.model.Library;
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

    @RequestMapping(path = "/hello")
    @ResponseBody
    public String hello() {
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
