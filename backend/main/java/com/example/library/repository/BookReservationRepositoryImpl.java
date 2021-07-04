package com.example.library.repository;

import com.example.library.model.BookReservation;
import com.example.library.model.Edition;
import org.apache.lucene.search.Query;
import org.hibernate.search.exception.SearchException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookReservationRepositoryImpl{

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public Map<Long, List<BookReservation>> searchReservations(String userId, String bookId, String bookTitle, String status, int page, int amount) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(BookReservation.class)
                .get();
        Query luceneQuery;
        List<BooleanJunction> queries = new ArrayList<>();
        if(userId != null) queries.add(queryBuilder.bool().must(queryBuilder.keyword().onField("user.member_id").matching(userId).createQuery()));
        if(bookId != null) queries.add(queryBuilder.bool().must(queryBuilder.keyword().onField("book.book_id").matching(bookId).createQuery()));
        if(bookTitle != null) queries.add( queryBuilder.bool().must(queryBuilder.keyword().onField("edition.title.title").matching(bookTitle).createQuery()));
        if(status != null) queries.add(queryBuilder.bool().must(queryBuilder.keyword().onField("reservationState").matching(status).createQuery()));

        BooleanJunction bj = queryBuilder.bool();
        for (BooleanJunction b: queries) {
            bj.must(b.createQuery()).must(bj.createQuery());
        }
        HashMap<Long, List<BookReservation>> retVal = new HashMap<>();
        try {
            luceneQuery = queryBuilder.bool().must(bj.createQuery()).createQuery();
            FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, BookReservation.class)
                    .setFirstResult((page - 1) * amount)
                    .setMaxResults(amount);
            List<BookReservation> result = jpaQuery.getResultList();
            retVal.put((long) jpaQuery.getResultSize(), result);
        } catch (SearchException se) {
            se.printStackTrace();
            retVal.put(0L, null);
        }
        return retVal;
    }
}
