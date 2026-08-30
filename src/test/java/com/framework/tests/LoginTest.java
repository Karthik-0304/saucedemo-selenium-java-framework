package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.driver.DriverFactory;
import com.framework.pages.LoginPage;
import com.framework.utils.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.FileNotFoundException;
import java.sql.Driver;
import java.util.Properties;


public class LoginTest extends BaseTest {

    protected Properties prop;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    @Test(priority = 1)
    @Description("Verify that a standard user can log in with valid credentials and successfully navigate to the inventory page.")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Authentication Module")
    public void loginTest() throws FileNotFoundException {
        logger.info("Start loginTest");
        LoginPage loginPage = new LoginPage();
        prop = ConfigReader.readProperties();

        String username =prop.getProperty("standard_user");
        String password = prop.getProperty("password");
        logger.info("username : " + username);
        logger.info("password : " + password);

        loginPage.loginToApp(username,password);


        String currentPage = DriverFactory.getDriver().getCurrentUrl().toString();
        logger.info("Current URL is: " + currentPage);

        System.out.println(currentPage);

        Assert.assertTrue(currentPage.contains("inventory"));
        logger.info("End loginTest");

    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidData() {
        return new Object[][] {
                { "locked_out_user", "secret_sauce" }, // Locked user
                { "invalid_user", "secret_sauce" },    // Wrong username
                { "standard_user", "wrong_password" }   // Wrong password
        };
    }

    @Test(dataProvider = "invalidLoginData",priority = 2)
    @Epic("Authentication Module")
    @Description("Verify that invalid credentials or locked-out users are blocked from logging in and correct error messages are displayed.")
    @Severity(SeverityLevel.CRITICAL)
    public  void testInvalidLoginData(String user, String pass) throws FileNotFoundException {
        logger.info("Start testInvalidLoginData");
        LoginPage loginPage = new LoginPage();

        logger.info("User name : " + user);
        logger.info("Password : " + pass);

        loginPage.loginToApp(user,pass);

        String error = loginPage.getErrorMessage();

        logger.info("Captured UI Error Message: " + error);
        System.out.println("UI Error Message: " + error);

        String currentPage = DriverFactory.getDriver().getCurrentUrl().toString();
        logger.info("Current URL is: " + currentPage);

        System.out.println(currentPage);

        Assert.assertFalse(currentPage.contains("inventory"));
        logger.info("End testInvalidLoginData");

    }
}
