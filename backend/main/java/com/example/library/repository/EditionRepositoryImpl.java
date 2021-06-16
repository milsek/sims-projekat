package com.example.library.repository;

import com.example.library.model.Edition;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EditionRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

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
