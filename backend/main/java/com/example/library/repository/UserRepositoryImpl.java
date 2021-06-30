package com.example.library.repository;

import com.example.library.model.Edition;
import com.example.library.model.User;
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
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Map<Long, List<User>> searchUsers(String text, int page, int amount) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Edition.class)
                .get();
        org.apache.lucene.search.Query luceneQuery;
        if (text.isEmpty())
        {
            luceneQuery = queryBuilder.all().createQuery();
        }
        else {
            text = "*" + text + "*";
            luceneQuery = queryBuilder
                    .keyword()
                    .fuzzy()
                    .onFields()
                    .boostedTo(5f)
                    .matching(text)
                    .createQuery();
        }

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Edition.class)
                .setFirstResult((page - 1) * amount)
                .setMaxResults(amount);
        List<User> result = jpaQuery.getResultList();
        HashMap<Long, List<User>> retVal = new HashMap<>();
        retVal.put((long) jpaQuery.getResultSize(), result);
        return retVal;
    }

}
