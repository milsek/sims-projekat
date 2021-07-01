package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class    EditionController {

    @Autowired
    private EditionService editionService;

    @GetMapping(path = "/all")
    @ResponseBody
    public List<Edition> getAll() {
        return editionService.getAll();
    }

    @GetMapping(path = "/edition/{id}")
    @ResponseBody
    public Edition getEdition(@PathVariable("id") long id) {
        return editionService.getEditionById(id);
    }

    @GetMapping(path = "/edition/related_to={id}")
    @ResponseBody
    public Set<Edition> getRelatedEditions(@PathVariable("id") long id)
    {
        return editionService.getRelatedEditions(id);
    }

}
