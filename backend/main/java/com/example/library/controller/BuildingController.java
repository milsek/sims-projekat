package com.example.library.controller;

import com.example.library.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;


}
