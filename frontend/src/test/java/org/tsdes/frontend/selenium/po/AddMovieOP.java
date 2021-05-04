package org.tsdes.frontend.selenium.po;

import org.tsdes.frontend.selenium.PageObject;

public class AddMovieOP extends LayoutPO {

    public AddMovieOP(PageObject other) {
        super(other);
    }

    public IndexPO createNewMovie(String title, String director, String date) {

        setText("title", title);
        setText("director", director);
        setText("date", date);
        clickAndWait("submit");

        IndexPO po = new IndexPO(this);
        if(po.isOnPage())
            return po;
        return null;
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Add movie");
    }
}
