package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * ProductPage
 * This class is used to keep functions and locators about Product page
 */
public class ProductPage extends HomePage {
    By addToBasket = By.xpath("//button[@class = 'add-to-basket']");
    By productContainer = By.xpath("//div[@class = 'product-container']");

    /**
     * Constructor of ProductPage
     *
     * @param driver: Driver instance to keep
     */
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * clickOnAddToBasket
     * Clicks on Add to Basket button
     */
    public void clickOnAddToBasket() {
        waitUntilProductContainerLoaded();
        customWait.waitUntilElementIsVisibleAndClickable(addToBasket, "clickOnAddToBasketNotFound");

        // To avoid stale element exception
        try {
            driver.findElement(addToBasket).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            customWait.waitUntilElementIsVisibleAndClickable(addToBasket, "clickOnAddToBasketNotFound");
            driver.findElement(addToBasket).click();
        }
    }

    /**
     * waitUntilProductContainerLoaded
     * Waits until product container element is loaded
     */
    public void waitUntilProductContainerLoaded() {
        customWait.waitUntilElementIsVisibleAndClickable(productContainer, "productContainerNotLoaded");
    }

    /**
     * closeProductBrowserTab
     * Closes newly opened tab which is opened by clicking a product on search page
     */
    public void closeProductBrowserTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}
