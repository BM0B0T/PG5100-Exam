package org.tsdes.frontend.selenium.po;


import org.openqa.selenium.By;
import org.tsdes.frontend.selenium.PageObject;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
public class SignUpPO extends LayoutPO {

    public SignUpPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Sign Up");
    }

    public IndexPO createUser(String userName, String password, String firstName, String lastName) {

        setText("username", userName);
        setText("password", password);
        setText("firstName", firstName);
        setText("lastName", lastName);
        clickAndWait("submit");

        IndexPO po = new IndexPO(this);
        if(po.isOnPage()){
            return po;
        }

        return null;
    }

    public boolean hasError() {
        return getDriver().findElements(By.id("error")).size() > 0;
    }
}
