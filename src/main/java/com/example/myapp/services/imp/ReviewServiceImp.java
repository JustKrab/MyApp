package com.example.myapp.services.imp;

import com.example.myapp.entities.Groups;
import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;
import com.example.myapp.repos.ReviewRepo;
import com.example.myapp.services.CommentsService;
import com.example.myapp.services.ReviewPhotoService;
import com.example.myapp.services.ReviewService;
import com.example.myapp.services.UserReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ReviewPhotoService reviewPhotoService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private UserReviewRatingService userReviewRatingService;

    public Review editReview(Long id, String text, String title, User author, String theme, String rating, Groups groups) {


        Review review = new Review(id, title, text, author, theme, rating, groups);
        return reviewRepo.save(review);
    }

    public Review add(String title, String text, User user, String theme, String rating, String group) {


        Review review = new Review(title, text, user, theme, rating);
        review.setGroups(Groups.valueOf(group));
        return reviewRepo.save(review);
    }

    public List<Review> findReviewByAuthor(User user) {
        return reviewRepo.findReviewByAuthor(user);
    }



    public Review findReviewById(Long id) {
        return reviewRepo.findReviewById(id);
    }

    public void saveReview(Review review) {
        reviewRepo.save(review);
    }

    public void removeReviewById(Long id) throws Exception {

        userReviewRatingService.removeById(id);
        commentsService.removeByReviewId(id);
        reviewPhotoService.removePhotoBuReviewId(id);
        reviewRepo.removeReviewById(id);
    }

    public List<Review> findLastAdded(){
        List<Review> last = findAll();
        Comparator<Review> comparator = Comparator.comparing(Review::getId);
        last.sort(comparator);
        List<Review> list = last.subList(Math.max(last.size() - 3, 0), last.size());
        Collections.reverse(list);
        return list.stream()
                .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
                .collect(Collectors.toList());
    }

    public List<Review> findMostPopular(){
        List<Review> popular = findAll();
        Comparator<Review> comparator = Comparator.comparing(val->userReviewRatingService.usersRating(val.getId()));
        popular.sort(comparator);
        List<Review> list = popular.subList(Math.max(popular.size() - 3, 0), popular.size());
        Collections.reverse(list);
        return list.stream()
                .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
                .collect(Collectors.toList());
    }



    public List<Review> findAll() {

        return reviewRepo.findAll();
    }

    public List<Review> findBooks() {

        return reviewRepo.findReviewByGroups(Groups.BOOKS).stream()
                .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
                .collect(Collectors.toList());
    }

    public List<Review> findGames() {
        return reviewRepo.findReviewByGroups(Groups.GAMES).stream()
                .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
                .collect(Collectors.toList());
    }

    public List<Review> findSerials() { return reviewRepo.findReviewByGroups(Groups.SERIALS).stream()
            .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
            .collect(Collectors.toList()); }

    public List<Review> findFilms() { return reviewRepo.findReviewByGroups(Groups.FILMS).stream()
            .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
            .collect(Collectors.toList());}
}
