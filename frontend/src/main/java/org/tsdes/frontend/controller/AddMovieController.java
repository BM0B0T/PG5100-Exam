package org.tsdes.frontend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.tsdes.backend.service.MovieService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class AddMovieController {
    @Autowired
    private MovieService movieService;

    private String title;
    private String director;
    private String releaseDate;


    public String addMovie() {
        System.out.println("test1");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(releaseDate, dateTimeFormatter);
        if(localDate == null){
            return "/addMovie.jsf?faces-redirect=true&error=true";
        }
        System.out.println("test 2");
        boolean success = movieService.createMovie(title.trim(), director, Date.valueOf(localDate));
        if(success)
            return "/index.jsf?faces-redirect=true";
        return "/addMovie.jsf?faces-redirect=true&error=true";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
