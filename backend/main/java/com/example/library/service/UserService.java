package com.example.library.service;

import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}
