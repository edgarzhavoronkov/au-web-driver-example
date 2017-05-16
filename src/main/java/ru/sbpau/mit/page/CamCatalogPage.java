package ru.sbpau.mit.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private static final By snippetsListLocator = By.xpath("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]");


    public CamCatalogPage(WebDriver webDriver) {
        driver = webDriver;
        driver.get(pageURL);
        driverWait = new WebDriverWait(driver, 5);
        brandFilter = new BrandFilter(driver, driverWait);
        priceFilter = new PriceFilter(driver);
    }

    public void setBrand(String brand) {
        brandFilter.setBrand(brand);
        waitForUpdate();
    }

    public void setPriceRange(int from, int to) {
        priceFilter.setPriceRange(from, to);
        waitForUpdate();
    }

    public List<CamSnippet> getResults() {
        waitForUpdate();
        List<WebElement> elems = driver.findElements(By.className("n-snippet-card"));
        List<CamSnippet> snippets = new ArrayList<>();
        for (WebElement elem : elems) {
            String name = elem.findElement(By.className("snippet-card__header-link")).getText().split(" ")[0];
            Double price = Double.parseDouble(elem.findElement(By.className("snippet-card__price")).getText().replaceAll("\\D+", ""));
            snippets.add(new CamSnippet(name, price));
        }
        return snippets;
    }

    private void waitForUpdate() {
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(snippetsListLocator));
    }
}
