package com.example.library.service;

import com.example.library.model.*;
import com.example.library.model.dto.*;
import com.example.library.repository.AccountRepository;
import com.example.library.repository.LibrarianRepository;
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
    private LibrarianRepository librarianRepository;

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
        List<Member> result = memberRepository.simpleSearch(query);
        return  result.stream()
                .map(x -> {return modelMapper.map(x, AutocompleteUserDto.class);})
                .collect(Collectors.toList());
    }

    public String registerMember(MemberRegisterDto memberRegisterDto) {
        try {
            Account account = modelMapper.map(memberRegisterDto, Account.class);
            Member member = modelMapper.map(memberRegisterDto, Member.class);
            member.setAccount(account);

            accountRepository.save(account);
            memberRepository.save(member);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Member registered.";
    }

    public String registerLibrarian(LibrarianRegisterDto librarianRegisterDto) {
        try {
            Account account = modelMapper.map(librarianRegisterDto, Account.class);
            Librarian librarian = modelMapper.map(librarianRegisterDto, Librarian.class);
            librarian.setAccount(account);

            accountRepository.save(account);
            librarianRepository.save(librarian);
        } catch (Exception e) {
            return "An error occurred.";
        }
        return "Librarian registered.";
    }

    private UserLoginDto entityToDto(User user) {
        return modelMapper.map(user, UserLoginDto.class);
    }

    private AutocompleteUserDto entityToDto(Member member) {
        return modelMapper.map(member, AutocompleteUserDto.class);
    }
}
