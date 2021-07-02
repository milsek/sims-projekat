package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.model.dto.AuthorDisplayDto;
import com.example.library.model.dto.EditionDisplayDto;
import com.example.library.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @Autowired
    private EditionService editionService;

    @GetMapping(path = "/topreads")
    @ResponseBody
    public List<EditionDisplayDto> getTopReads() {
        return editionService.getTopReads();
    }

    @GetMapping(path = "/topten")
    @ResponseBody
    public List<EditionDisplayDto> getTopTen() {
        return editionService.getTopTen();
    }

    @GetMapping(path = "/popular-authors")
    @ResponseBody
    public Set<AuthorDisplayDto> getPopularAuthors() {
        return editionService.getPopularAuthors();
    }
}
