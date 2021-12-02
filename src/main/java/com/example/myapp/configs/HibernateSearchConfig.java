package com.example.myapp.configs;

import com.example.myapp.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;


@Configuration
public class HibernateSearchConfig {

    @Autowired
    private EntityManagerFactory entityManager;


    @Bean
    SearchService hibernateSearchService() {
        SearchService hibernateSearchService = new SearchService(this.entityManager);
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }

}
