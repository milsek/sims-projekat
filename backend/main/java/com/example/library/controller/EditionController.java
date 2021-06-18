package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping(path = "/edition/{id}")
    @ResponseBody
    public Edition getEdition(@PathVariable("id") long id) {
        return editionService.getEdition(id);
    }

    @GetMapping(path = "fullSearch")
    @ResponseBody
    public Map<Long, List<Edition>> searchEditions(@RequestParam(name = "text", required = false) String text,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "amount") int amount)
    {
        if(text == null)
            text = "";
       return new HashMap<Long, List<Edition>>(editionService.searchEditions(text, page, amount));
    }


}
