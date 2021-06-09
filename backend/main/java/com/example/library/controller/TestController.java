package com.example.library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    @RequestMapping(path = "/hello")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String hello() {
        return "Hello:)";
    }

}
