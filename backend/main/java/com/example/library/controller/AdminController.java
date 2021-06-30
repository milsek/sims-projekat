package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Member;
import com.example.library.model.User;
import com.example.library.service.AccountService;
import com.example.library.service.BookService;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/user_search/")
    @ResponseBody
    public List<Member> userQuery(@RequestParam(name = "query") String query) {
        return memberService.searchMembers(query);
    }

    @GetMapping(path = "/search_book/")
    @ResponseBody
    public Set<Book> findBookId(@RequestParam(name = "query") String id) {
        return bookService.findBookId(id + "%");
    }

    @GetMapping(path="/get_book/")
    @ResponseBody
    public Book findBookById(@RequestParam(name = "query", required = false) String id) {
        System.out.println(id);
        if(id.isEmpty()) return null;
        return bookService.findBookById(id);
    }

}
