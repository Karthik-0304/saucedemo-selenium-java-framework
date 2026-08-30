package com.framework.listerners;

import com.framework.driver.DriverFactory;
import com.framework.utils.Screenshotutility;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListerners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println("Test failed : " + tr.getName());

        // Save to local disk via utility
        Screenshotutility.captureScreenshot(tr.getName());

        // Attach Screenshot to Allure Report
        byte[] screenshotByte = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshotByte), ".png");

        // Attach Text Log to Allure Report
        Allure.addAttachment("Test Execution Log", "text/plain", "Test failed due to assertion or exception in method: " + tr.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        System.out.println("Test success : " + tr.getName());
        Allure.addAttachment("Test Execution Log", "text/plain", "Test passed successfully: " + tr.getMethod().getMethodName());
    }
}