package ru.sbpau.mit.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author - Эдгар
 * Date - 14.05.2017, 13:50
 */
public class BrandFilter {
    private final WebDriver driver;
    private final WebDriverWait driverWait;

    private final By moarButtonLocator = By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div[1]/div/div[4]/div[2]/div/div[2]/button");
    private final By inputFieldLocator = By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div[1]/div/div[4]/div[2]/div/span/span/input");
    private final By brandsListLocator = By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div[1]/div/div[4]/div[2]/div/div[1]/div");


    public BrandFilter(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
    }

    public void setBrand(String brand) {
        driver.findElement(moarButtonLocator).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(inputFieldLocator));
        driver.findElement(inputFieldLocator).sendKeys(brand);
        driverWait.withTimeout(5, TimeUnit.SECONDS);
        List<WebElement> elems = driver.findElement(brandsListLocator).findElements(By.cssSelector(".n-filter-block__item"));
        for (WebElement elem : elems) {
            if (elem.getText().equals(brand)) {
                elem.findElement(By.className("checkbox__label")).click();
            }
        }
    }
}
