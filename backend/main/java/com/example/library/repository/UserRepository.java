package com.example.library.repository;

import com.example.library.model.Reservation;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByAccount_Id(Long account_id);

}
