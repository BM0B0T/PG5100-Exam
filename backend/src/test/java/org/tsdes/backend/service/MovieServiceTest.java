package org.tsdes.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tsdes.backend.StubApplication;
import org.tsdes.backend.entity.Movie;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MovieServiceTest extends ServiceTestBase {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("see if there is no movie")
    void noMovie() {
        List <Movie> movieList = movieService.getAllMoviesByAvgRating();
        assertEquals(0, movieList.size());
    }

    @Test
    @DisplayName("add movie test")
    void addMovieTest() {
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
    }

    @Test
    @DisplayName("add and delete movie test")
    void addAndDeleteMovieTest() {
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertTrue(movieService.deleteMovie("Deadpool"));
        assertFalse(movieService.deleteMovie("Deadpool"));
    }

    @Test
    @DisplayName("cant add to movies with same title")
    void cantAddToMoviesWithSameTitle() {
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertFalse(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
    }


    @Test
    @DisplayName("get movie by title test")
    void getMovieByTitleTest() {
        assertNull(movieService.getMovieByTitle("Deadpool"));
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertNotNull(movieService.getMovieByTitle("Deadpool"));
    }

    @Test
    @DisplayName("get movies by review test")
    void getMoviesByReviewTest() {
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertTrue(movieService.createMovie("Deadpool2", "David Leitch", Date.valueOf("2018-06-12")));
        List <Movie> movieList = movieService.getAllMoviesByAvgRating();
        assertEquals(2, movieList.size());
    }

    @Test
    @DisplayName("get highest rated movie by review test")
    void getHighestRatedMovieByReviewTest() {
        assertTrue(movieService.createMovie("Deadpool2", "David Leitch", Date.valueOf("2018-06-12")));
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertTrue(userService.createUser("user@gmail.com", "password1", "test", "tests"));
        assertNotNull(reviewService.addReview("Deadpool", "its funny", 5, "user@gmail.com"));

        Movie movie = movieService.getMovieByTitle("Deadpool");
        List <Movie> movieList = movieService.getAllMoviesByAvgRating();

        assertEquals(movie.getTitle(), movieList.get(0).getTitle());
        assertEquals(movie.getDirector(), movieList.get(0).getDirector());
        assertEquals(movie.getYearOfRelease(), movieList.get(0).getYearOfRelease());
    }
}
