package com.example.myapp.entityes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class UserReviewRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(columnDefinition = "VARCHAR(255)")
    private String userRating;


    @Column(columnDefinition = "VARCHAR(255)")
    private String liked;

    @ManyToOne(targetEntity = Review.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id",referencedColumnName = "id")
    private Review review;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public UserReviewRating() {
    }

    public UserReviewRating(String userRating, String liked,User user,Review review) {
        this.userRating = userRating;
        this.liked = liked;
        this.user=user;
        this.review=review;
    }
}
