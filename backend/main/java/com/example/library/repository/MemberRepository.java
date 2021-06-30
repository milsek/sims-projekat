package com.example.library.repository;

import com.example.library.model.Member;
import com.example.library.model.Reservation;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Map<Long, List<Member>> searchMembers(String text, int page, int amount);
}
