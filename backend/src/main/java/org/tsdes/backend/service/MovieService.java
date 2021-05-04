package org.tsdes.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {
    @Autowired
    private EntityManager em;

    @Autowired
    private ReviewService reviewService;

    public boolean createMovie(String title, String director, Date yearOfRelease) {
        if(em.find(Movie.class, title) != null)
            return false;
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setYearOfRelease(yearOfRelease);
        em.persist(movie);
        return true;
    }

    public boolean deleteMovie(String title) {
        Movie movie = em.find(Movie.class, title);
        if(movie == null)
            return false;
        Query q = em.createQuery("delete from Review r where r.targetMovie = ?1");
        q.setParameter(1, movie);
        q.executeUpdate();
        em.remove(movie);
        return true;
    }

    public List <Movie> getAllMoviesByAvgRating() {
        List <Movie> movies = em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
        return movies.stream()
                .sorted(Comparator.comparing(m -> reviewService.averageRating((Movie) m)).reversed())
                .collect(Collectors.toList());
    }

    public Movie getMovieByTitle(String title) {
        return em.find(Movie.class, title);
    }
}
