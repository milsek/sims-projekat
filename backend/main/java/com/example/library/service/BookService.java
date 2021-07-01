package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.dto.SelectedBookDto;
import com.example.library.repository.BookRepository;
import com.example.library.repository.EditionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EditionRepository editionRepository;

//    public Set<Book> findLikeID(String id) {
//        return bookRepository.findLikeID(id);
//    }

    public SelectedBookDto findBookById(String id) {
        return entityToDto(bookRepository.findById(Long.valueOf(id)).get());
    }

    public Set<Book> autocompleteBookId(String id) {
        Set<Book> books = bookRepository.findByIdStartingWith(id + "%");
        for (Book book : books) {
            book.setTitle(book.getEdition().getTitle().getTitle());
        }
        return books;
    }

    private SelectedBookDto entityToDto(Book book) {
        return modelMapper.map(book, SelectedBookDto.class);
    }
}
