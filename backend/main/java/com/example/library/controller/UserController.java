package com.example.library.controller;

import com.example.library.model.dto.LibrarianRegisterDto;
import com.example.library.model.dto.MemberRegisterDto;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register/member/")
    private String registerMember(@RequestBody MemberRegisterDto memberRegisterDto) {
        return userService.registerMember(memberRegisterDto);
    }

    @PostMapping("register/librarian/")
    private String registerLibrarian(@RequestBody LibrarianRegisterDto librarianRegisterDto) {
        return userService.registerLibrarian(librarianRegisterDto);
    }

}
