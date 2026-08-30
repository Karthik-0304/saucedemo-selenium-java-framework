package com.framework.pages;

import com.framework.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public WebDriverWait wait;

    public BasePage() {
        this.wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
    }

    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void doubleClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).doubleClick().perform();
    }

    public String getTextOfTheElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element.getAttribute("innerText");
    }

    public void selectActionByValue(String action,By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(element);
        select.selectByValue(action);
    }

    public List<WebElement> getListOfElements(By locator) {
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return elements;
    }
}
