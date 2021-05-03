package org.tsdes.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.service.MovieService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MovieController {
    @Autowired
    private MovieService movieService;

    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovieFromTitle(String title) {
        movie = movieService.getMovieByTitle(title);
    }
}
