package com.example.myapp.services.imp;

import com.example.myapp.entities.Review;
import com.example.myapp.services.SearchService;
import com.example.myapp.services.UserReviewRatingService;
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
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    private UserReviewRatingService userReviewRatingService;

    private final EntityManager entityManager;

    @Autowired
    public SearchServiceImp(final EntityManagerFactory entityManagerFactory) {
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
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(1).onFields("theme", "text", "title")
                .matching(searchTerm).createQuery();


        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Review.class);


        List<Review> ReviewList = null;
        try {
            ReviewList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            return null;
        }


        return ReviewList.stream()
                .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
                .collect(Collectors.toList());



    }
}
