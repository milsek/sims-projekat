package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book b where to_char(b.book_id) like ?1 limit 5", nativeQuery = true)
    public Set<Book> findByIdStartingWith(String id);


}
