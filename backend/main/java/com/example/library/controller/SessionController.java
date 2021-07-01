package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.model.dto.UserLoginDto;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SessionController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/login/")
    @ResponseBody
    public UserLoginDto login(@RequestParam(name = "mail") String mail,
                      @RequestParam(name = "password") String password,
                      HttpServletResponse response) {
        UserLoginDto userLoginDto = userService.login(mail, password);

        if(userLoginDto != null) {

            Cookie nameCookie = new Cookie("mail", mail);
            nameCookie.setHttpOnly(false);
            nameCookie.setSecure(false);
            nameCookie.setDomain("localhost");
            nameCookie.setPath("/");
            nameCookie.setMaxAge(30*24*3600);
            response.addCookie(nameCookie);

            Cookie roleCookie = new Cookie("role", String.valueOf(userLoginDto.getDiscriminatorValue()));
            roleCookie.setDomain("localhost");
            roleCookie.setHttpOnly(false);
            roleCookie.setSecure(false);
            roleCookie.setPath("/");
            roleCookie.setMaxAge(30*24*3600);
            response.addCookie(roleCookie);
        }
        return userLoginDto;
    }

}
