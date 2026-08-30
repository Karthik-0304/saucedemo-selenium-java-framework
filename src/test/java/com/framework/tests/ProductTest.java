package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.LoginPage;
import com.framework.pages.ProductsPage;
import com.framework.utils.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ProductsPage.class);

    @Test
    @Epic("Inventory Module")
    @Description("Verify that the product inventory loads successfully and items can be accurately sorted using the filter options.")
    public void sortTest() throws FileNotFoundException {
        logger.info("Starting test");
        prop = ConfigReader.readProperties();
        LoginPage loginPage = new LoginPage();
        loginPage.loginToApp(prop.getProperty("standard_user"), prop.getProperty("password"));
        ProductsPage productsPage = new ProductsPage();
        List<String> prices = productsPage.selectSort();
        ArrayList<Double> actualprice = new ArrayList<>();

        for (String price : prices) {
            String cleanedPrice =  price.replace("$", "").trim();
            System.out.println("Cleaned Price : "+cleanedPrice);
            actualprice.add(Double.parseDouble(cleanedPrice));
        }


        for (int i = 0; i <actualprice.size()-1; i++) {
            Assert.assertTrue(actualprice.get(i)<=actualprice.get(i+1));
        }
        logger.info("Finished test");
    }


}
