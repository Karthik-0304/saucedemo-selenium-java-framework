package com.framework.driver;

import com.framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileNotFoundException;
import java.util.Properties;

public class DriverFactory {

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver setDriver(String browser) {
        boolean isHeadless = false;
        try {
            Properties prop = ConfigReader.readProperties();
            isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false"));
        } catch (FileNotFoundException e) {
            // default to false if config not found
        }

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            threadLocalDriver.set(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("-headless");
            }
            threadLocalDriver.set(new FirefoxDriver(options));
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            threadLocalDriver.set(new EdgeDriver(options));
        } else if (browser.equalsIgnoreCase("safari")) {
            threadLocalDriver.set(new SafariDriver());
        }

        return threadLocalDriver.get();
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void closeDriver() {
        if (threadLocalDriver.get() != null) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }
}