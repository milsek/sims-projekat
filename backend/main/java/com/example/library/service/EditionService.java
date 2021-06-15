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

    public List<Edition> getTopTen() {
        return editionRepository.findTop10ByOrderByRatingDesc();
    }

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

    @Transactional
    public List<Edition> searchEditions(String text, int page, int amount) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Edition.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .fuzzy()
                .onFields("title", "description", "tags.name",
                        "author.name", "contributors.name", "publisher.name")
                .boostedTo(5f)
                .matching("*" + text + "*")
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Edition.class)
                .setFirstResult((page - 1) * amount)
                .setMaxResults(amount);
        List<Edition> result = jpaQuery.getResultList();

        return result;
    }
}
