package com.example.library.controller;

import com.example.library.model.Edition;
import com.example.library.model.User;
import com.example.library.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
            nameCookie.setHttpOnly(false);
            nameCookie.setSecure(false);
            nameCookie.setDomain("localhost");
            nameCookie.setPath("/");
            nameCookie.setMaxAge(30*24*3600);
            response.addCookie(nameCookie);

            Cookie roleCookie = new Cookie("role", String.valueOf(user.getDiscriminatorValue()));
            roleCookie.setDomain("localhost");
            roleCookie.setHttpOnly(false);
            roleCookie.setSecure(false);
            roleCookie.setPath("/");
            roleCookie.setMaxAge(30*24*3600);
            response.addCookie(roleCookie);
        }
        return user;
    }
}
