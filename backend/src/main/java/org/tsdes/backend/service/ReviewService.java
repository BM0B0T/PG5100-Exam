package org.tsdes.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.entity.Review;
import org.tsdes.backend.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private EntityManager em;

    public boolean addReview(String title, String reviewText, int rating, String author) {
        Movie movie = em.find(Movie.class, title);
        if(movie == null)
            return false;
        User user = em.find(User.class, author);
        if(user == null)
            return false;
        Review review = new Review();
        review.setTargetMovie(movie);
        review.setReviewText(reviewText);
        review.setRating(rating);
        review.setAuthor(user);
        em.persist(review);
        return true;
    }

    public Double averageRating(Movie movie) {
        List <Review> reviews = getAllReviewByMovie(movie);
        double sum = reviews.stream().mapToDouble(Review::getRating).sum();
        return sum / reviews.size();
    }

    public List <Review> getAllReviewByMovie(@NotNull Movie movie) {
        Query q = em.createQuery("SELECT r FROM Review r WHERE r.targetMovie = ?1", Review.class);
        q.setParameter(1, movie);
        return q.getResultList();
    }


}
