package com.example.myapp.entityes;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


@Getter
@Setter
@Entity
@Indexed
@Table(name = "review")
@Transactional
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String theme;

    @Field
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    @Column(columnDefinition = "VARCHAR(255)")
    private String rating;

    @Field
    @Column(columnDefinition = "VARCHAR(2048) NOT NULL")
    private String text;

    @Field
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @Enumerated(EnumType.STRING)
    private Groups groups;


    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewPhotos> photos;


    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<Comments> comments;


    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<UserReviewRating> userRating;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;


    public String getAuthorName() {
        return author != null ? author.getUsername() : "unknown user";
    }


    public Review() {
    }

    public Review(Long id, String title, String text, User author, String theme,String rating,Groups groups) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.author = author;
        this.theme = theme;
        this.rating=rating;
        this.groups=groups;
    }

    public Review(String title, String text, User author, String theme, String rating) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.theme = theme;
        this.rating = rating;
    }


    public Review(String theme, String title, String rating, String text, User author) {
        this.theme = theme;
        this.title = title;
        this.rating = rating;
        this.text = text;
        this.author = author;

    }
}
