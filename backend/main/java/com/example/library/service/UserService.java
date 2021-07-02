package com.example.library.service;

import com.example.library.model.Account;
import com.example.library.model.Edition;
import com.example.library.model.Member;
import com.example.library.model.User;
import com.example.library.model.dto.AutocompleteUserDto;
import com.example.library.model.dto.SearchEditionDto;
import com.example.library.model.dto.UserLoginDto;
import com.example.library.repository.AccountRepository;
import com.example.library.repository.MemberRepository;
import com.example.library.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<AutocompleteUserDto> autocompleteUserId(String query) {
        AutocompleteUserDto dto = new AutocompleteUserDto();
        Map<Long, List<Member>> result = memberRepository.searchMembers(query, 1, 5);
        List<Member> members = result.get(result.keySet().toArray()[0]);
        return members.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    private UserLoginDto entityToDto(User user) {
        return modelMapper.map(user, UserLoginDto.class);
    }

    private AutocompleteUserDto entityToDto(Member member) {
        return modelMapper.map(member, AutocompleteUserDto.class);
    }
}
