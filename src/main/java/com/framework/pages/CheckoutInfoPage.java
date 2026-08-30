package com.framework.pages;

import org.openqa.selenium.By;

public class CheckoutInfoPage extends BasePage {
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public void enterShippingInfo(String fName, String lName, String postalCode) {
        type(firstNameField, fName);
        type(lastNameField, lName);
        type(postalCodeField, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }
}