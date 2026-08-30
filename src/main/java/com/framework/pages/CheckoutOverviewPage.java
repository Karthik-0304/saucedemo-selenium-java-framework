package com.framework.pages;

import org.openqa.selenium.By;

public class CheckoutOverviewPage extends BasePage {
    private By finishButton = By.id("finish");
    private By successHeader = By.cssSelector(".complete-header");

    public void clickFinish() {
        click(finishButton);
    }

    public String getSuccessHeader() {
        return getTextOfTheElement(successHeader);
    }
}