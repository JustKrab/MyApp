package com.example.myapp.services;

import com.example.myapp.entities.Review;

import java.util.List;

public interface SearchService {

    void initializeHibernateSearch();

    List<Review> fuzzySearch(String searchTerm);
}
