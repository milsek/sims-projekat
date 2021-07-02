package com.example.library.repository;

import com.example.library.model.BookTitle;
import com.example.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public interface BookTitleRepository extends JpaRepository<BookTitle, Long> {

    public Set<BookTitle> findBookTitlesByGenresIn(HashSet<Genre> genres);
}
