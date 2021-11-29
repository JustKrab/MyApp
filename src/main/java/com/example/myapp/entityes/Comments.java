package com.example.myapp.entityes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "VARCHAR(2048) NOT NULL")
    private String text;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private LocalDate time;

    @ManyToOne(targetEntity = Review.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id",referencedColumnName = "id")
    private Review review;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public Comments() {
    }

    public Comments(String text, LocalDate time, Review review, User user) {
        this.text = text;
        this.time = time;
        this.review = review;
        this.user = user;
    }
}
