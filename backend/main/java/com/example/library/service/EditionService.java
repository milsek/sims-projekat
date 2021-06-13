package com.example.library.service;

import com.example.library.model.Edition;
import com.example.library.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionService {

    @Autowired
    private EditionRepository editionRepository;

    public List<Edition> getTopTen() {
        return editionRepository.findTop10ByOrderByRatingDesc();
    }

    public List<Edition> getTopReads() {
        return editionRepository.findTop16ByOrderByReadsDesc();
    }

    public List<Edition> getAll() {
        return editionRepository.findAll();
    }
}
