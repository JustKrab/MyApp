package com.example.myapp.services;

import com.example.myapp.entityes.Review;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Getter
@Setter
@Service
public class SearchService {


    private final EntityManager entityManager;

    @Autowired
    public SearchService(final EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @PostConstruct
    public void initializeHibernateSearch() {

        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Review> fuzzySearch(String searchTerm) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Review.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(1).onFields("theme","text","title")
                .matching(searchTerm).createQuery();

//        FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term("theme",searchTerm));
//        fuzzyQuery.
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Review.class);

        // execute search

        List<Review> ReviewList = null;
        try {
            ReviewList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }

        return ReviewList;


    }
}
