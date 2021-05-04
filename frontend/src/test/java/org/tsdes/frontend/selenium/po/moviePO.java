package org.tsdes.frontend.selenium.po;

import org.openqa.selenium.WebDriver;
import org.tsdes.frontend.selenium.PageObject;

public class moviePO extends LayoutPO {

    public moviePO(PageObject other) {
        super(other);
    }

    public moviePO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

}
