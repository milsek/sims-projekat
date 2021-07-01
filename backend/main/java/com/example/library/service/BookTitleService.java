package com.example.library.service;

import com.example.library.model.BookTitle;
import com.example.library.model.Edition;
import com.example.library.repository.BookTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BookTitleService {
    @Autowired
    private BookTitleRepository titleRepository;

    public Set<Edition> getAllEditionsByTitleId(Long id) {
        Optional<BookTitle> result = titleRepository.findById(id);
        if (result.isEmpty()) {
            return null;
        }
        return result.get().getEditions();
    }

}
