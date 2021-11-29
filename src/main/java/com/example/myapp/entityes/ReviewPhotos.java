package com.example.myapp.entityes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "photos")
public class ReviewPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String filename;

    @ManyToOne(targetEntity = Review.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id",referencedColumnName = "id")
    private Review review;

    public ReviewPhotos() {
    }

    public ReviewPhotos(String filename, Review review) {
        this.filename = filename;
        this.review = review;
    }
}
