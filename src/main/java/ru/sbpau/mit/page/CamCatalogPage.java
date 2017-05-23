package ru.sbpau.mit.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbpau.mit.elements.CamSnippet;
import ru.sbpau.mit.elements.BrandFilter;
import ru.sbpau.mit.elements.PriceFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author - Эдгар
 * Date - 14.05.2017, 13:51
 */
public class CamCatalogPage {
    private final WebDriver driver;
    private final WebDriverWait driverWait;
    private final BrandFilter brandFilter;
    private final PriceFilter priceFilter;

    private static final String pageURL = "https://market.yandex.ru/catalog/54761/list?local-offers-first=0&deliveryincluded=0&onstock=1";
    private static final By snippetsListLocator = By.xpath("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]/div[1]");
    private static final By resultsCounterLocator = By.className("n-filter-panel-counter");
    private static final By moarSnippetsLocator = By.xpath("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]/div[4]/div[1]/a");


    public CamCatalogPage(WebDriver webDriver) {
        driver = webDriver;
        driver.get(pageURL);
        driverWait = new WebDriverWait(driver, 10);
        brandFilter = new BrandFilter(driver, driverWait);
        priceFilter = new PriceFilter(driver);
    }

    public void setBrand(String brand) {
        brandFilter.setBrand(brand);
        waitForUpdate();
    }

    public void setPriceRange(int from, int to) {
        priceFilter.setMinPrice(from);
        waitForUpdate();
        priceFilter.setMaxPrice(to);
        waitForUpdate();
    }

    public List<CamSnippet> getResults() {
        waitForUpdate();
        List<CamSnippet> snippets = new ArrayList<>();
        while (driver.findElement(moarSnippetsLocator).isDisplayed()) {
            driver.findElement(moarSnippetsLocator).click();
        }
        for (WebElement elem : driver.findElements(By.className("n-snippet-card"))) {
            String name = elem.findElement(By.className("snippet-card__header-link")).getText().split("\\s")[0];
            Double price = Double.parseDouble(elem.findElement(By.className("snippet-card__price")).getText().replaceAll("\\D+", ""));
            snippets.add(new CamSnippet(name, price));
        }
        waitForUpdate();
        return snippets;
    }

    private void waitForUpdate() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(resultsCounterLocator));
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(snippetsListLocator));
            driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("n-snippet-card")));
        } catch (TimeoutException ignored) {
        }
    }
}
