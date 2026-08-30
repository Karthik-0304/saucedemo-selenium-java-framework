package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private By sortButton = By.className("product_sort_container");
    private By priceList = By.xpath("//div[@class='pricebar']//div");
    private By addToCartOnesie = By.id("add-to-cart-sauce-labs-onesie");
    private By cartIcon = By.className("shopping_cart_link");

    public List<String> selectSort() {
        selectActionByValue("lohi", sortButton);
        List<WebElement> priceListElements = getListOfElements(priceList);
        List<String> priceValues = new ArrayList<>();

        for (WebElement element : priceListElements) {
            priceValues.add(element.getText());
        }
        return priceValues;
    }

    public void addOnesieToCart() {
        click(addToCartOnesie);
    }

    public void openCart() {
        click(cartIcon);
    }
}