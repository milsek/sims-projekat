package com.example.library.repository;

import com.example.library.model.Edition;
import com.example.library.model.Member;
import com.example.library.model.User;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Component
public class MemberRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Map<Long, List<Member>> searchMembers(String text, int page, int amount) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Member.class)
                .get();
        org.apache.lucene.search.Query luceneQuery;
        if (text.isEmpty())
        {
            luceneQuery = queryBuilder.all().createQuery();
            HashMap<Long, List<Member>> retVal = new HashMap<>();
            retVal.put(0L, new ArrayList<Member>());
            return retVal;
        }
        else {
            text = text;
            luceneQuery = queryBuilder
                    .keyword()
                    .fuzzy()
                    .withEditDistanceUpTo(1)
                    .onFields("member_id", "member_name", "member_surname")
                    .matching(text)
                    .createQuery();
        }

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Member.class)
                .setFirstResult((page - 1) * amount)
                .setMaxResults(amount);
        List<Member> result = jpaQuery.getResultList();
        HashMap<Long, List<Member>> retVal = new HashMap<>();
        retVal.put((long) jpaQuery.getResultSize(), result);
        return retVal;
    }

}
