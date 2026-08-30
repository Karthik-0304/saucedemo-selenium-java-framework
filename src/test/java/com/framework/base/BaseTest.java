package com.framework.base;

import com.framework.driver.DriverFactory;
import com.framework.utils.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {
    protected Properties prop;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws FileNotFoundException {
        prop = ConfigReader.readProperties();

        // If browser parameter is empty, fallback to properties file or default "chrome"
        if (browser == null || browser.isEmpty()) {
            browser = prop.getProperty("browser");
            if (browser == null) {
                browser = "chrome";
            }
        }

        DriverFactory.setDriver(browser);
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.closeDriver();
    }
}