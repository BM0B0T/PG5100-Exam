package org.tsdes.intro.exercises.quizgame.selenium.po;

import org.openqa.selenium.WebDriver;
import org.tsdes.intro.exercises.quizgame.selenium.PageObject;


public class IndexPO extends LayoutPO {

    public IndexPO(PageObject other) {
        super(other);
    }

    public IndexPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public void toStartingPage() {
        toOrigin();
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("home");
    }

}
