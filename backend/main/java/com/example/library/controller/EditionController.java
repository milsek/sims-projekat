package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EditionController {

    @Autowired
    private EditionService editionService;

    @GetMapping(path = "/topreads")
    @ResponseBody
    public List<Edition> getTopReads() {
        return editionService.getTopReads();
    }

    @GetMapping(path = "/topten")
    @ResponseBody
    public List<Edition> getTopTen() {
        return editionService.getTopTen();
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public List<Edition> getAll() {
        return editionService.getAll();
    }


}
