package com.example.library.repository;

import com.example.library.model.BookReservation;
import com.example.library.model.Edition;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookReservationRepositoryImpl{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Map<Long, List<BookReservation>> searchReservations(String text, int page, int amount) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(BookReservation.class)
                .get();
        org.apache.lucene.search.Query luceneQuery;
        if (text.isEmpty())
        {
            luceneQuery = queryBuilder.all().createQuery();
        }
        else {
            luceneQuery = queryBuilder
                    .keyword()
                    .onFields("book.id", "book.edition.title.title", "dateTaken", "dateReturned")
                    .boostedTo(5f)
                    .matching(text)
                    .createQuery();
        }

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, BookReservation.class)
                .setFirstResult((page - 1) * amount)
                .setMaxResults(amount);
        List<BookReservation> result = jpaQuery.getResultList();
        HashMap<Long, List<BookReservation>> retVal = new HashMap<>();
        retVal.put((long) jpaQuery.getResultSize(), result);
        return retVal;
    }
}
