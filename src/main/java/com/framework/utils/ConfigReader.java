package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;



    public  static Properties readProperties() throws FileNotFoundException {
        String fileName ="src/main/resources/config.properties";
        prop = new Properties();
        try{
            FileInputStream fis = new FileInputStream(fileName);
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
