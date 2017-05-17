package ru.sbpau.mit.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Author - Эдгар
 * Date - 14.05.2017, 13:50
 */
public class PriceFilter {
    private final WebDriver driver;

    private final By minPriceLocator = By.xpath("//*[@id=\"glf-pricefrom-var\"]");
    private final By maxPriceLocator = By.xpath("//*[@id=\"glf-priceto-var\"]");

    public PriceFilter(WebDriver driver) {
        this.driver = driver;
    }


    public void setMinPrice(Integer minPrice) {
        driver.findElement(minPriceLocator).sendKeys(minPrice.toString());
    }

    public void setMaxPrice(Integer maxPrice) {
        driver.findElement(maxPriceLocator).sendKeys(maxPrice.toString());
    }
}
