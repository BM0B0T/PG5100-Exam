package org.tsdes.frontend.selenium;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tsdes.frontend.selenium.po.IndexPO;
import org.tsdes.frontend.selenium.po.MoviePO;
import org.tsdes.frontend.selenium.po.SignUpPO;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public abstract class SeleniumTestBase {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private IndexPO home;

    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();

    private String getUniqueId() {
        return "Selenium@LocalIT_" + counter.getAndIncrement() + ".com";
    }

    private IndexPO createNewUser(String username, String password, String firstName, String lastName) {

        home.toStartingPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO = signUpPO.createUser(username, password, firstName, lastName);
        assertNotNull(indexPO);

        return indexPO;
    }

    private String createNewReviewWithANewUser(String reviewText, int rating) {
        assertFalse(home.isLoggedIn());
        String username = getUniqueId();
        String password = "password1";
        String firstName = "test";
        String lastName = "test";
        home = createNewUser(username, password, firstName, lastName);
        assertTrue(home.isLoggedIn());

        MoviePO moviePO = goToFirstMovie();
        assertTrue(moviePO.isOnPage());
        assertTrue(moviePO.canPostReview());
        assertTrue(moviePO.postReview(reviewText, rating));
        findReview(reviewText, rating, username);
        home = moviePO.doLogout();
        return username;
    }

    private void findReview(String review, int rating, String username) {
        assertTrue(getDriver().getPageSource().contains("Review: " + review));
        assertTrue(getDriver().getPageSource().contains("Rating: " + rating));
        assertTrue(getDriver().getPageSource().contains("Email: " + username));
    }

    private MoviePO goToFirstMovie() {
        home.toStartingPage();
        return home.firstMovie();
    }

    @BeforeEach
    public void initTest() {

        getDriver().manage().deleteAllCookies();

        home = new IndexPO(getDriver(), getServerHost(), getServerPort());

        home.toStartingPage();

        assertTrue(home.isOnPage(), "Failed to start from Home Page");
    }

    @Test
    public void testCreateAndLogoutUser() {
        assertFalse(home.isLoggedIn());
        String username = getUniqueId();
        String password = "password1";
        String firstName = "test";
        String lastName = "test";
        home = createNewUser(username, password, firstName, lastName);
        assertTrue(home.isLoggedIn());
        home.doLogout();
        assertFalse(home.isLoggedIn());
    }

    @Test
    public void testCantCreateSameUserOnSameUsername() {
        assertFalse(home.isLoggedIn());
        String username = getUniqueId();
        String password = "password1";
        String firstName = "test";
        String lastName = "test";
        home = createNewUser(username, password, firstName, lastName);
        assertTrue(home.isLoggedIn());
        home.doLogout();
        assertFalse(home.isLoggedIn());
        home.toStartingPage();
        SignUpPO signUpPO = home.toSignUp();
        signUpPO.createUser(username, password, firstName, lastName);
        assertTrue(signUpPO.isOnPage());
        assertTrue(signUpPO.hasError());
    }

    @Test
    @DisplayName("test default movies")
    void testDefaultMovies() {
        assertTrue(home.isOnPage());
        assertTrue(home.haveMovies());
    }
    @Test
    @DisplayName("test cant make review without review text")
    void testCantMakeReviewWithoutReviewText() {
        assertTrue(home.isOnPage());
        assertFalse(home.isLoggedIn());
        String username = getUniqueId();
        String password = "password1";
        String firstName = "test";
        String lastName = "test";
        home = createNewUser(username, password, firstName, lastName);

        assertTrue(home.isLoggedIn());
        MoviePO moviePO = goToFirstMovie();
        assertTrue(moviePO.isOnPage());
        assertTrue(moviePO.canPostReview());
        moviePO.postReview("",1);
        assertEquals("All fields need to be populated",moviePO.getText("text-error"));
        moviePO.postReview("its bad",1);
        moviePO.postReview("its bad",1);
        assertEquals("Cant make more then 1 review per movie",moviePO.getText("user-error"));
    }


    @Test
    @DisplayName("test write review")
    void testWriteReview() {
        MoviePO moviePO = goToFirstMovie();
        assertTrue(moviePO.isOnPage());
        assertFalse(moviePO.canPostReview());
        home = moviePO.toHomepage();

        assertFalse(home.isLoggedIn());
        String username = getUniqueId();
        String password = "password1";
        String firstName = "test";
        String lastName = "test";
        home = createNewUser(username, password, firstName, lastName);
        assertTrue(home.isLoggedIn());
        moviePO = goToFirstMovie();
        assertTrue(moviePO.isOnPage());
        assertTrue(moviePO.canPostReview());
        assertTrue(moviePO.postReview("testing a review", 5));
        findReview("testing a review", 5, username);
        home = moviePO.doLogout();

        moviePO = goToFirstMovie();
        assertTrue(moviePO.isOnPage());
        assertFalse(moviePO.canPostReview());
        findReview("testing a review", 5, username);
    }

    @Test
    @DisplayName("test stars")
    void testStars() {
        assertTrue(home.isOnPage());
        String stars = getDriver().findElement(By.xpath("/html/body/div[1]/p[3]/label")).getText();
        createNewReviewWithANewUser("testing a review", 5);
        assertTrue(home.isOnPage());
        String newStars = getDriver().findElement(By.xpath("/html/body/div[1]/p[3]/label")).getText();
        assertNotEquals(stars, newStars);
    }

    @Test
    @DisplayName("test sorting")
    void testSorting() {
        goToFirstMovie();
        List <String> preRes = getDriver().findElements(By.className("review")).stream().map(WebElement::getText).collect(Collectors.toList());

        createNewReviewWithANewUser("testing a review 1", 5);
       createNewReviewWithANewUser("testing a review 2", 2);
       createNewReviewWithANewUser("testing a review 3", 1);

        MoviePO moviePO = goToFirstMovie();

        moviePO.clickAndWait("sort-rating");
        List <String> res = getDriver().findElements(By.className("review")).stream().map(WebElement::getText).collect(Collectors.toList());
        res = res.stream().filter(x -> !preRes.contains(x)).collect(Collectors.toList());
        assertTrue(res.get(0).contains("Rating: 5"));
        assertTrue(res.get(1).contains("Rating: 2"));
        assertTrue(res.get(2).contains("Rating: 1"));

        moviePO.clickAndWait("sort-time");
        res = getDriver().findElements(By.className("review")).stream().map(WebElement::getText).collect(Collectors.toList());
        res = res.stream().filter(x -> !preRes.contains(x)).collect(Collectors.toList());
        assertTrue(res.get(0).contains("Rating: 1"));
        assertTrue(res.get(1).contains("Rating: 2"));
        assertTrue(res.get(2).contains("Rating: 5"));
    }


}
