package org.tsdes.frontend.selenium.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tsdes.frontend.selenium.PageObject;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
public abstract class LayoutPO extends PageObject {

    public LayoutPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public LayoutPO(PageObject other) {
        super(other);
    }

    public SignUpPO toSignUp() {
        clickAndWait("linkToSignupId");
        SignUpPO po = new SignUpPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public AddMovieOP toAddMovie() {
        clickAndWait("add-movie");
        AddMovieOP po = new AddMovieOP(this);
        assertTrue(po.isOnPage());
        return po;
    }

    public MoviePO firstMovie() {
        clickAndWaitXpath("/html/body/div[1]/form/input[2]");
        MoviePO po = new MoviePO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public MoviePO secondMovie() {
        clickAndWaitXpath("/html/body/div[2]/form/input[2]");
        MoviePO po = new MoviePO(this);
        assertTrue(po.isOnPage());
        return po;
    }

    public IndexPO doLogout() {
        clickAndWait("logoutId");
        IndexPO po = new IndexPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public boolean isLoggedIn() {
        return getDriver().findElements(By.id("logoutId")).size() > 0 &&
                getDriver().findElements((By.id("linkToSignupId"))).isEmpty();
    }

    public boolean haveMovies() {
        return getDriver().findElements(By.className("title")).size() >= 2;
    }
}
