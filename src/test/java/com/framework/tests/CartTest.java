package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.CartPage;
import com.framework.pages.LoginPage;
import com.framework.pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class CartTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CartTest.class);

    @Test(priority = 1)
    @Epic("Cart Module")
    @Description("Verify that a product added from the inventory page appears correctly in the shopping cart.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyItemAddedToCart() throws FileNotFoundException {
        logger.info("Start verifyItemAddedToCart test");

        LoginPage loginPage = new LoginPage();
        logger.info("Logging into application as standard user");
        loginPage.loginToApp(prop.getProperty("standard_user"), prop.getProperty("password"));

        ProductsPage productsPage = new ProductsPage();
        logger.info("Adding Sauce Labs Onesie to the cart");
        productsPage.addOnesieToCart();

        logger.info("Opening shopping cart");
        productsPage.openCart();

        CartPage cartPage = new CartPage();
        String actualItemName = cartPage.getCartItemName();
        logger.info("Fetched item name from cart: " + actualItemName);

        Assert.assertEquals(actualItemName, "Sauce Labs Onesie", "The item in the cart does not match!");
        logger.info("Cart verification test passed successfully. End verifyItemAddedToCart");
    }
}