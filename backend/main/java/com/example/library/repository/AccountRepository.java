package com.example.library.repository;

import com.example.library.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findAccountByMailAndPassword(String mail, String password);

}
