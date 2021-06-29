package com.example.library.service;

import com.example.library.model.Account;
import com.example.library.model.User;
import com.example.library.repository.AccountRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public User login(String mail, String password) {
        Account account = accountRepository.findAccountByMailAndPassword(mail, password);
        if (account == null) {
            return null;
        }
        return userRepository.findByAccount_Id(account.getId());
    }

    public Set<User> getAll() {
        Set<User> set = new HashSet<>();
        userRepository.findAll().addAll(set);
        return set;
    }
}
