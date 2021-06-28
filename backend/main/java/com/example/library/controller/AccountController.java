package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.model.User;
import com.example.library.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/login/")
    @ResponseBody
    public User getEdition(@RequestParam(name = "mail") String mail,
                           @RequestParam(name = "password") String password) {
        return accountService.login(mail, password);
    }
}
