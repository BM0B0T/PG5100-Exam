package org.tsdes.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tsdes.backend.StubApplication;
import org.tsdes.backend.entity.Review;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class sortServiceTest  extends ServiceTestBase{
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private SortService sortService;

    @Test
    @DisplayName("test sorting")
    void testSorting() {
        assertTrue(movieService.createMovie("Deadpool", "Tim Miller", Date.valueOf("2016-02-12")));
        assertTrue(userService.createUser("user@gmail.com", "password1", "test", "tests"));
        assertTrue(userService.createUser("user2@gmail.com", "password1", "test", "tests"));
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test", 4, userService.getUser("user@gmail.com")));
        assertNotNull(reviewService.addReview(movieService.getMovieByTitle("Deadpool"), "test2", 2, userService.getUser("user2@gmail.com")));

        List <Review> reviewList = reviewService.getAllReviewByMovie(movieService.getMovieByTitle("Deadpool"));
        List <Review> resList = sortService.sortByTimestamp(reviewList);
        resList.forEach((x) -> assertNotEquals(reviewList.get(resList.indexOf(x)).getReviewText(), x.getReviewText()));

        List <Review> resList2 = sortService.sortByRating(resList);
        resList2.forEach((x) -> assertNotEquals(resList.get(resList2.indexOf(x)).getReviewText(), x.getReviewText()));
    }

}
