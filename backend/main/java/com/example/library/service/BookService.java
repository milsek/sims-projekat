package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BookState;
import com.example.library.model.dto.AutocompleteBookDto;
import com.example.library.model.dto.SelectedBookDto;
import com.example.library.repository.BookRepository;
import com.example.library.repository.EditionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EditionRepository editionRepository;

    @Autowired
    private ReservationService reservationService;

    public SelectedBookDto findBookById(String id) {
        return entityToSelectedBookDto(bookRepository.findById(Long.valueOf(id)).get());
    }

    public Set<AutocompleteBookDto> autocompleteBookId(String id) {
        Set<Book> books = bookRepository.findByIdStartingWith(id + "%");
        return books.stream().map(this::entityToAutocompleteBookDto).collect(Collectors.toSet());
    }

    private SelectedBookDto entityToSelectedBookDto(Book book) {
        return modelMapper.map(book, SelectedBookDto.class);
    }

    private AutocompleteBookDto entityToAutocompleteBookDto(Book book) {
        return modelMapper.map(book, AutocompleteBookDto.class);
    }

    public SelectedBookDto returnBook(String id) {
        Optional<Book> book = bookRepository.findById(Long.valueOf(id));
        if(book.isEmpty()) {
            return null;
        }
        Book b = book.get();
        b.setBookState(BookState.IN_STOCK);
        bookRepository.saveAndFlush(b);
        reservationService.reservationReturned(b);
        b = bookRepository.findById(Long.valueOf(id)).get();
        return modelMapper.map(b, SelectedBookDto.class);
    }
}
