package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.model.User;
import com.example.library.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/login/")
    @ResponseBody
    public User login(@RequestParam(name = "mail") String mail,
                           @RequestParam(name = "password") String password,
                           HttpServletResponse response) {
        User user = accountService.login(mail, password);
        if(user != null) {
            Cookie nameCookie = new Cookie("mail", mail);
            response.addCookie(nameCookie);
            Cookie roleCookie = new Cookie("role", String.valueOf(user.getDiscriminatorValue()));
            response.addCookie(roleCookie);
        }
        return user;
    }
}
