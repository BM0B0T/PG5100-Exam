package org.tsdes.frontend.selenium;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.tsdes.frontend.selenium.po.IndexPO;
import org.tsdes.frontend.selenium.po.SignUpPO;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


public abstract class SeleniumTestBase {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private IndexPO home;

    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();

    private String getUniqueId() {
        return "Selenium@LocalIT_" + counter.getAndIncrement()+".com";
    }

    private IndexPO createNewUser(String username, String password, String firstName, String lastName) {

        home.toStartingPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO = signUpPO.createUser(username, password, firstName, lastName);
        assertNotNull(indexPO);

        return indexPO;
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
}
