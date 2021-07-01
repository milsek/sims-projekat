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

//    public Set<Book> findLikeID(String id) {
//        return bookRepository.findLikeID(id);
//    }

    public Book findBookById(String id) {
        System.out.println(id);
        System.out.println(Long.valueOf(id));
        return bookRepository.findById(Long.valueOf(id)).get();
    }

    public Set<Book> autocompleteBookId(String id) {
        Set<Book> books =  bookRepository.findByIdStartingWith(id + "%");
        for (Book book: books) {
            book.setTitle(book.getEdition().getTitle().getTitle());
        }
        return books;
    }
}
