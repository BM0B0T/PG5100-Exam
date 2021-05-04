package org.tsdes.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tsdes.backend.StubApplication;
import org.tsdes.backend.entity.Review;
import org.tsdes.backend.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ReviewServiceTest extends ServiceTestBase {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    public User createUser(String username) {
        assertTrue(userService.createUser(username, "password1", "test", "tests"));
        return userService.getUser(username);
    }

    @Test
    @DisplayName("test to create a review")
    void testToCreateAReview() {
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        createUser("user@gmail.com");
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test", 4, userService.getUser("user@gmail.com")));
    }

    @Test
    @DisplayName("test that its null if not a author")
    void testThatItsNullIfNotAAuthor() {
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test", 4, null));
    }

    @Test
    @DisplayName("test that its null if not a movieTitle")
    void testThatItsNullIfNotAMovieTitle() {
        User user1 = createUser("user@gmail.com");
        assertNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test", 4, user1));
    }

    @Test
    @DisplayName("getting avg of a movie")
    void gettingAvgOfAMovie() {
        User user1 = createUser("user@gmail.com");
        User user2 = createUser("user1@gmail.com");
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test", 4, user1));
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test1", 2, user2));
        assertEquals(3.0, reviewService.averageRating(movieService.getMovieByTitle("Deadpool")));
    }

    @Test
    @DisplayName("get all reviews by movie")
    void getAllReviewsByMovie() {
        User user1 = createUser("user@gmail.com");
        User user2 = createUser("user1@gmail.com");
        User user3 = createUser("user2@gmail.com");
        User user4 = createUser("user3@gmail.com");
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertTrue(movieService.createMovie("Deadpool2", "David Leitch", Date.valueOf("2018-06-12")));
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test2", 4, user1));
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test1", 2, user2));
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test3", 3, user3));
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool2"), "test4", 3, user4));

        List <Review> reviewList = reviewService.getAllReviewByMovie(movieService.getMovieByTitle("Deadpool"));
        assertEquals(3, reviewList.size());
        assertFalse(reviewList.stream().map(Review::getReviewText).anyMatch((x) -> x.equals("test4")));
        Review review = reviewList.stream().filter(x -> x.getReviewText().equals("test1")).collect(Collectors.toList()).get(0);
        assertEquals("test1", review.getReviewText());
        assertEquals("Deadpool", review.getTargetMovie().getTitle());
        assertEquals(2, review.getRating());
        assertEquals("user1@gmail.com", review.getAuthor().getUsername());
        assertNotNull(review.getTimestamp());
        assertNotNull(review.getId());

    }


}
