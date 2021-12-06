package com.example.myapp.configs;

import com.example.myapp.services.SearchService;
import com.example.myapp.services.imp.SearchServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
public class HibernateSearchConfig {

    @Autowired
    private EntityManagerFactory entityManager;


    @Bean
    SearchServiceImp hibernateSearchService() {
        SearchServiceImp hibernateSearchService = new SearchServiceImp(this.entityManager);
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }

}
