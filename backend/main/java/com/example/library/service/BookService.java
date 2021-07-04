package com.example.library.service;

import com.example.library.model.*;
import com.example.library.model.dto.AutocompleteBookDto;
import com.example.library.model.dto.BookChangePlacementDto;
import com.example.library.model.dto.SelectedBookDto;
import com.example.library.repository.BookRepository;
import com.example.library.repository.EditionRepository;
import com.example.library.repository.IsleRepository;
import com.example.library.repository.LineRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private IsleRepository isleRepository;

    @Autowired
    private EditionRepository editionRepository;

    @Autowired
    private ReservationService reservationService;

    public SelectedBookDto findBookById(String id) {
        return entityToSelectedBookDto(bookRepository.findById(Long.valueOf(id)).get());
    }

    public List<AutocompleteBookDto> autocompleteBookId(String id) {
        List<Book> books = bookRepository.findByIdStartingWith(id + "%");
        return books.stream().map(this::entityToAutocompleteBookDto).collect(Collectors.toList());
    }

    private SelectedBookDto entityToSelectedBookDto(Book book) {
        return modelMapper.map(book, SelectedBookDto.class);
    }

    private AutocompleteBookDto entityToAutocompleteBookDto(Book book) {
        return modelMapper.map(book, AutocompleteBookDto.class);
    }

    public String changeBookPlacement(BookChangePlacementDto dto, Long bookId) {
        try {
            Book book = bookRepository.getById(bookId);

            Section section = book.getLine().getIsle().getSection();

            Isle isle = isleRepository.findBySectionAndName(section, dto.getIsleName());
            Line line = lineRepository.findByIsleAndNumber(isle, dto.getLineNumber());

            book.setLine(line);
            bookRepository.save(book);
        } catch (Exception e) {
            return "An error occurred.";
        }
        return "Book placement changed.";
    }

    public Page<SelectedBookDto> searchBook(String id, String title, String author, String status, Pageable paging) {
        Page<Book> bookPage = bookRepository.searchBook(id, title, author, status, paging);
        Page<SelectedBookDto> dtoPage = bookPage.map(new Function<Book, SelectedBookDto>() {
            @Override
            public SelectedBookDto apply(Book book) {
                return modelMapper.map(book, SelectedBookDto.class);
            }
        });
        return dtoPage;
    }
}
