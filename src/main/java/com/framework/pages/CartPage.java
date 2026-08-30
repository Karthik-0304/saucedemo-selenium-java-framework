package com.framework.pages;

import org.openqa.selenium.By;

public class CartPage extends BasePage {
    private By cartItemName = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public String getCartItemName() {
        return getTextOfTheElement(cartItemName);
    }

    public void clickCheckout() {
        click(checkoutButton);
    }
}