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

@Service
@Transactional
public class ReviewService {
    @Autowired
    private EntityManager em;

    public Review addReview(Movie movie, String reviewText, int rating, User user) {
        if(movie == null || user == null || !canUserMakeReview(user, movie)) return null;

        Review review = new Review();
        review.setTargetMovie(movie);
        review.setReviewText(reviewText);
        review.setRating(rating);
        review.setAuthor(user);
        review.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        em.persist(review);
        return review;
    }

    public double averageRating(Movie movie) {
        Query q = em.createQuery("SELECT AVG(r.rating) FROM Review r WHERE r.targetMovie = ?1");
        q.setParameter(1, movie);
        Double res = (Double) q.getSingleResult();
        if(res == null)
            return 0.0;
        return res;
    }

    public List <Review> getAllReviewByMovie(Movie movie) {
        Query q = em.createQuery("SELECT r FROM Review r WHERE r.targetMovie = ?1", Review.class);
        q.setParameter(1, movie);
        return q.getResultList();
    }

    public boolean canUserMakeReview(User user, Movie movie) {
        Query q = em.createQuery("SELECT r FROM Review r WHERE r.targetMovie = ?1 AND r.Author = ?2", Review.class);
        q.setParameter(1, movie);
        q.setParameter(2, user);
        return q.getResultList().isEmpty();
    }
}
