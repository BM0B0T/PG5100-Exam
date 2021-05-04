package org.tsdes.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.annotation.SessionScope;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.entity.Review;
import org.tsdes.backend.entity.User;
import org.tsdes.backend.service.FilterService;
import org.tsdes.backend.service.MovieService;
import org.tsdes.backend.service.ReviewService;
import org.tsdes.backend.service.UserService;

import javax.inject.Named;
import java.util.List;

@Named
@SessionScope
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private FilterService filterService;
    @Autowired
    private UserService userService;


    private List <Movie> movies = null;
    private List <Review> reviews = null;
    private Movie selectedMovie = null;


    private String reviewText;
    private Integer reviewRating;


    public String sendReview() {
        User user = userService.getUser(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        System.out.println(selectedMovie);
        if(reviewText.length() == 0)
            return "/movie.jsf?faces-redirect=true&error=true";
        Review result = reviewService.addReview(selectedMovie, reviewText, reviewRating, user);
        reviewText = null;
        reviewRating = null;
        if(result == null)
            return "/movie.jsf?faces-redirect=true&user-error=true";
        refreshReviews();
        return "/movie.jsf?faces-redirect=true";
    }


    public List <Movie> getMovies() {
        if(movies == null)
            movies = movieService.getAllMoviesByAvgRating();
        return movies;
    }

    public boolean isMovieSelected() {
        return selectedMovie != null;
    }

    public String selectMovie(Movie movie) {
        selectedMovie = movie;
        refreshReviews();
        return "/movie.jsf?faces-redirect=true";
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public String avgRating(Movie movie) {
        return String.format("%.2f", reviewService.averageRating(movie));
    }


    public List <Review> getReviews() {
        if(reviews == null)
            reviews = reviewService.getAllReviewByMovie(selectedMovie);
        return reviews;
    }

    public void filterByRating() {
        System.out.println("rating");
        System.out.println(reviews.size());
        reviews = filterService.filterByRating(reviews);
    }

    public void filterByTime() {
        System.out.println("time");
        reviews = filterService.filterByTimestamp(reviews);
    }

    public String refreshMovies() {
        movies = movieService.getAllMoviesByAvgRating();
        return "/index.jsf?faces-redirect=true";
    }

    public String refreshReviews() {
        reviews = null;
        getReviews();
        return "/index.jsf?faces-redirect=true";
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Integer getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Integer reviewRating) {
        this.reviewRating = reviewRating;
    }
}
