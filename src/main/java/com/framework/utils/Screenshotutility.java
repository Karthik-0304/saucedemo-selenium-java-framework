package com.framework.utils;

import com.framework.driver.DriverFactory;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;


public class Screenshotutility {

    public static String captureScreenshot(String testName) {

        File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

        String desFile = System.getProperty("user.dir") + "\\src\\main\\java\\com\\screenshots\\" + testName + "_" + System.currentTimeMillis()+".png";

        try {
            FileUtils.copyFile(srcFile, new File(desFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  desFile;
    }
}
