package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/edition")
@CrossOrigin(origins = "http://localhost:3000")
public class EditionController {

    @Autowired
    private EditionService editionService;

    @GetMapping(path = "/all")
    @ResponseBody
    public List<Edition> getAll() {
        return editionService.getAll();
    }

    @GetMapping(path = "/")
    @ResponseBody
    public Edition getEditionById(@RequestParam("id") long id) {
        return editionService.getEditionById(id);
    }

    @GetMapping(path = "/related-to/")
    @ResponseBody
    public Set<Edition> getRelatedEditions(@RequestParam("id") long id)
    {
        return editionService.getRelatedEditions(id);
    }

}
