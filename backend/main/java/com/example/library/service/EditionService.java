package com.example.library.service;

import com.example.library.model.Edition;
import com.example.library.repository.EditionRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class EditionService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private EditionRepository editionRepository;

//    @Autowired
//    private PublisherRepository publisherRepository;

    public List<Edition> getTopTen() {
        //addTestEdition();
        return editionRepository.findTop10ByOrderByRatingDesc();
    }

//    public void addTestEdition() {
//        Edition edition = new Edition();
//        edition.setId(120L);
//        edition.setTitle("Pavle");
//        edition.setDescription("nesto");
//        edition.setRating(0.0);
//        edition.setPublisher(publisherRepository.getById(0L));
//        editionRepository.save(edition);
//    }


    public List<Edition> getTopReads() {
        return editionRepository.findTop16ByOrderByReadsDesc();
    }

    public List<Edition> getAll() {
        return editionRepository.findAll();
    }

    public Edition getEdition(long id) {
        Optional<Edition> result = editionRepository.findById(id);
        return result.orElse(null);
    }

    public List<Edition> searchEditions(String text, int page, int amount) {
        return editionRepository.searchEditions(text, page, amount);
    }
}
