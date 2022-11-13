package org.example.com.page;

import com.cognifide.qa.bb.aem.core.pages.AemAuthorPage;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.cognifide.qa.bb.wait.BobcatWait;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageObject
public class TPage extends AemAuthorPage<TPage> {

    @Inject
    private BobcatWait bobcatWait;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDisplayed() {
        return bobcatWait.isConditionMet(ExpectedConditions.titleIs(getTitle()));
    }

    public void actions(String script, String css) {
        WebElement element = webDriver.findElement(By.cssSelector(css));
        executeScript(script, element);
    }

    public String executeScript(String script, Object... args) {
        return String.valueOf(getJsExecutor().executeScript(script, args));
    }

    public JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) webDriver;
    }

    public TPage openSite() {
        this.webDriver.get(this.authorUrl + "/sites.html" + this.getFullUrl());
        return this;
    }
}
