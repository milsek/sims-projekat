package com.example.library.service;

import com.example.library.model.Member;
import com.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> searchMembers(String query) {
        Map<Long, List<Member>> result = memberRepository.searchMembers(query, 1, 5);
        return result.get(result.keySet().toArray()[0]);
    }
}
