package com.framework.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginPage extends BasePage {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");


    public void loginToApp(String user, String pass) {
        type(usernameField, user);   // Inherited from BasePage
        type(passwordField, pass);   // Inherited from BasePage
        click(loginButton);          // Inherited from BasePage
    }

    public String getErrorMessage() {
        return getTextOfTheElement(errorMessage);
    }



}
