package ru.sbpau.mit.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Author - Эдгар
 * Date - 14.05.2017, 13:50
 */
public class PriceFilter {
    private final WebDriver driver;

    private final By fromLocator = By.xpath("//*[@id=\"glf-pricefrom-var\"]");
    private final By toLocator = By.xpath("//*[@id=\"glf-priceto-var\"]");

    public PriceFilter(WebDriver driver) {
        this.driver = driver;
    }


    public void setPriceRange(Integer from, Integer to) {
        driver.findElement(fromLocator).sendKeys(from.toString());
        driver.findElement(toLocator).sendKeys(to.toString());
    }
}
