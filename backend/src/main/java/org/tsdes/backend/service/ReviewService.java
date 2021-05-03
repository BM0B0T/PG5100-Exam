package org.tsdes.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.entity.Review;
import org.tsdes.backend.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private EntityManager em;

    public Review addReview(String movieTitle, String reviewText, int rating, String author) {
        Movie movie = em.find(Movie.class, movieTitle);
        if(movie == null)
            return null;
        User user = em.find(User.class, author);
        if(user == null)
            return null;
        Review review = new Review();
        review.setTargetMovie(movie);
        review.setReviewText(reviewText);
        review.setRating(rating);
        review.setAuthor(user);
        review.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        em.persist(review);
        return review;
    }

    public int averageRating(Movie movie) {
        Query q = em.createQuery("select avg(r.rating) from Review r where r.targetMovie = ?1");
        q.setParameter(1, movie);
        return q.getFirstResult();
    }

    public List <Review> getAllReviewByMovie(Movie movie) {
        Query q = em.createQuery("SELECT r FROM Review r WHERE r.targetMovie = ?1", Review.class);
        q.setParameter(1, movie);
        return q.getResultList();
    }


}
