package ru.sbpau.mit;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sbpau.mit.elements.CamSnippet;
import ru.sbpau.mit.page.CamCatalogPage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Author - Эдгар
 * Date - 14.05.2017, 13:51
 */
public class CamCatalogPageTest {
    private WebDriver driver;
    private CamCatalogPage page;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        page = new CamCatalogPage(driver);
    }

    @Test
    public void testSimple() throws Exception {
        page.setBrand("Sony");
        page.setPriceRange(10000, 50000);

        List<CamSnippet> cards = page.getResults();

        assertFalse(cards.isEmpty());

        for (CamSnippet card : cards) {
            assertEquals("Sony", card.getBrand());
            assertTrue(card.getPrice() >= 10000 && card.getPrice() <= 50000);
        }
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}