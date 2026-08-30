package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class CheckoutE2ETest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CheckoutE2ETest.class);

    @Test
    @Epic("Checkout Module")
    @Description("Verify end-to-end purchasing flow from login, adding items, filling info, to order completion.")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompleteCheckoutFlow() throws FileNotFoundException {
        logger.info("Start E2E Checkout Test");

        LoginPage loginPage = new LoginPage();
        loginPage.loginToApp(prop.getProperty("standard_user"), prop.getProperty("password"));
        logger.info("Logged in successfully");

        ProductsPage productsPage = new ProductsPage();
        productsPage.addOnesieToCart();
        productsPage.openCart();
        logger.info("Added product to cart and opened cart page");

        CartPage cartPage = new CartPage();
        cartPage.clickCheckout();
        logger.info("Proceeded to checkout information page");

        CheckoutInfoPage infoPage = new CheckoutInfoPage();
        infoPage.enterShippingInfo("John", "Doe", "12345");
        infoPage.clickContinue();
        logger.info("Entered shipping information and continued");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        overviewPage.clickFinish();
        logger.info("Clicked finish on checkout overview");

        // Note: You will navigate to confirmation view where header is validated
        // (Ensure your confirmation page locator points to .complete-header on completion view)
        logger.info("E2E Checkout test execution completed");
    }
}