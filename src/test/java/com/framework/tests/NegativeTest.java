package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.driver.DriverFactory;
import com.framework.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class NegativeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(NegativeTest.class);

    @Test
    @Epic("Regression Module")
    @Description("Deliberate negative test to verify screenshot capture on assertion failure.")
    @Severity(SeverityLevel.NORMAL)
    public void intentionalFailureTest() throws FileNotFoundException {
        logger.info("Starting intentional failure test to verify screenshot attachment");

        LoginPage loginPage = new LoginPage();
        loginPage.loginToApp(prop.getProperty("standard_user"), prop.getProperty("password"));

        // Intentional false assertion to trigger failure and snapshot capture
        logger.info("Asserting incorrect title to force test failure");
        Assert.assertEquals(DriverFactory.getDriver().getTitle(), "Wrong Title For Testing", "Title mismatch intentional failure!");
    }
}