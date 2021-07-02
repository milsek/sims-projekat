package com.example.library.repository;

import com.example.library.model.Edition;
import org.apache.lucene.search.Query;
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
public class EditionRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Map<Long, List<Edition>> searchEditions(String text, int page, int amount) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Edition.class)
                .get();

        Query luceneQuery;
        if (text.isEmpty())
        {
            luceneQuery = queryBuilder.all().createQuery();
        }
        else {
            luceneQuery = queryBuilder
                    .keyword()
                    .onFields("title.title", "description", "tags.name", "contributions.contributor.name",
                            "title.contributions.contributor.name", "publisher.name")
                    .boostedTo(5f)
                    .matching(text)
                    .createQuery();
        }

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Edition.class)
                .setFirstResult((page - 1) * amount)
                .setMaxResults(amount);
        List<Edition> result = jpaQuery.getResultList();
        HashMap<Long, List<Edition>> retVal = new HashMap<>();
        retVal.put((long) jpaQuery.getResultSize(), result);
        return retVal;
    }

//    @Transactional
//    public Map<Long, List<Edition>> searchEditions(String text, int page, int amount){
//
//        SearchSession searchSession = Search.session( entityManager );
//
//        SearchResult<Book> result = searchSession.search( Book.class )
//                .where( f -> f.match()
//                        .field( "title" )
//                        .matching( "robot" ) )
//                .fetch( 20 );
//
//        long totalHitCount = result.total().hitCount();
//        List<Book> hits = result.hits();
//    }
}
