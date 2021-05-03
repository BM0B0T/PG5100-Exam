package org.tsdes.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.service.MovieService;
import org.tsdes.backend.service.ReviewService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class indexController {
    @Autowired
    private MovieService movieService;

    private List <Movie> movies = null;

    @Autowired
    private ReviewService reviewService;
    private Movie selectedMovie = null;

    public List <Movie> getMovies() {
        if(movies == null)
            movies = movieService.getAllMoviesByAvgRating();
        return movies;
    }

    public String setMovie(Movie movie) {
        selectedMovie = movie;
        return "/ui/movie.jsf?movie="+movie.getTitle()+"&faces-redirect=true";
    }

    public Movie getMovie() {
        return selectedMovie;
    }


    public int avgRating(Movie movie) {
        return reviewService.averageRating(movie);
    }

}
