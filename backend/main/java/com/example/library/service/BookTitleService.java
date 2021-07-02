package com.example.library.service;

import com.example.library.model.BookTitle;
import com.example.library.model.Edition;
import com.example.library.model.Genre;
import com.example.library.repository.BookTitleRepository;
import com.example.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookTitleService {
    @Autowired
    private BookTitleRepository titleRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Set<Edition> getAllEditionsByTitleId(Long id) {
        Optional<BookTitle> result = titleRepository.findById(id);
        if (result.isEmpty()) {
            return null;
        }
        return result.get().getEditions();
    }

    public Set<Edition> getEditionsByGenreId(Long id) {
        Genre genre = genreRepository.getById(id);
        HashSet<Genre> set = new HashSet<>();
        set.add(genre);
        Set<BookTitle> titles = titleRepository.findBookTitlesByGenresIn(set);
        Set<Edition> editions = new HashSet<>();
        for (BookTitle title : titles) {
            editions.addAll(title.getEditions());
        }
        System.out.println(editions.size());
        return editions;
    }

}
