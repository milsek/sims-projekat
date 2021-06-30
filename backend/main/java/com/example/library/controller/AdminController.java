package com.example.library.controller;

import com.example.library.model.Member;
import com.example.library.model.User;
import com.example.library.service.AccountService;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MemberService memberService;

    @GetMapping(path = "/user_search/")
    @ResponseBody
    public List<Member> userQuery(@RequestParam(name = "query") String query) {
        return memberService.searchMembers(query);
    }

}
