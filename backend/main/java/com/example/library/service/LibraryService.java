package com.example.library.service;

import com.example.library.model.Library;
import com.example.library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public void insertLibrary(Library library) {
        libraryRepository.save(library);
    }

    public Library getLibraryById(long id) {
        return libraryRepository.findById(id).orElse(null);
    }
}
