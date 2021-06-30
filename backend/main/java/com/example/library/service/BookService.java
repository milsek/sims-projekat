package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EditionRepository editionRepository;

    public Set<Book> findLikeID(String id) {
        return bookRepository.findLikeID(id);
    }

    public Set<Book> findBookId(String id) {
        Set<Book> books =  bookRepository.findLikeID(id);
        for (Book book: books) {
            book.setTitle(book.getEdition().getTitle().getTitle());
        }
        return books;
    }
}
