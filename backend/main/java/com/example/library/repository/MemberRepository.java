package com.example.library.repository;

import com.example.library.model.Member;
import com.example.library.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
