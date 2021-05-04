package org.tsdes.frontend.selenium.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tsdes.frontend.selenium.PageObject;

public class MoviePO extends LayoutPO {

    public MoviePO(PageObject other) {
        super(other);
    }

    public MoviePO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Movie");
    }

    public boolean canPostReview() {
        return getDriver().getPageSource().contains("Review text");
    }

    public IndexPO toHomepage() {
        IndexPO po = new IndexPO(this);
        po.toStartingPage();
        if(po.isOnPage()){
            return po;
        }
        return null;
    }


    public boolean postReview(String reviewText, int rating) {
        int sizeOfReviews = getDriver().findElements(By.className("review")).size();
        setText("review-text", reviewText);
        getDriver().findElement(By.xpath("/html/body/form[1]/div/select/option[" + rating + "]")).click();
        clickAndWait("submit");
        return getDriver().findElements(By.className("review")).size() > sizeOfReviews;
    }
}
