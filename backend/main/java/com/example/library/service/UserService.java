package com.example.library.service;

import com.example.library.model.Account;
import com.example.library.model.Member;
import com.example.library.model.User;
import com.example.library.model.dto.UserLoginDto;
import com.example.library.repository.AccountRepository;
import com.example.library.repository.MemberRepository;
import com.example.library.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AccountRepository accountRepository;

    public UserLoginDto login(String mail, String password) {
        Account account = accountRepository.findAccountByMailAndPassword(mail, password);
        if (account == null) {
            return null;
        }
        User user = userRepository.findByAccount_Id(account.getId());
        return entityToDto(user);
    }

    public Set<User> getAll() {
        Set<User> set = new HashSet<>();
        userRepository.findAll().addAll(set);
        return set;
    }

    public List<Member> autocompleteUserId(String query) {
        Map<Long, List<Member>> result = memberRepository.searchMembers(query, 1, 5);
        return result.get(result.keySet().toArray()[0]);
    }

    private UserLoginDto entityToDto(User user) {
        return modelMapper.map(user, UserLoginDto.class);
    }
}
