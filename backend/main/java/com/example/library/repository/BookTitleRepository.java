package com.example.library.repository;

import com.example.library.model.BookTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTitleRepository extends JpaRepository<BookTitle, Long> {
}
