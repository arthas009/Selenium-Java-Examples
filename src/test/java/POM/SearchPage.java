package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * SearchPage
 * This class is used to keep functions and locators about Search page
 */
public class SearchPage extends HomePage {
    By randomProduct = By.xpath("//div[@class = 'p-card-wrppr with-campaign-view']/div[1]");
    By productCenterWrapper = By.xpath("//div[@class = 'search-app-container']");
    By pricesDiv = By.xpath("//div[@class = 'fltr-cntnr-ttl-area']");
    By minPriceInput = By.xpath("//input[@class = 'fltr-srch-prc-rng-input min']");
    By maxPriceInput = By.xpath("//input[@class = 'fltr-srch-prc-rng-input max']");
    By clickOnSearchInPrices = By.xpath("//button[@class = 'fltr-srch-prc-rng-srch']");
    By favoriteButton = By.xpath("//i[@class = 'fvrt-btn']");

    /**
     * Constructor of SearchPage
     *
     * @param driver: Driver instance to keep
     */
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * clickOnBrandCheckbox
     * Clicks on checkbox of given brand name
     */
    public void clickOnBrandCheckbox(String brandName) {
        By checkBox = By.xpath("//div[@class = 'fltr-item-text' and text() = '" + brandName + "']/parent::a");
        customWait.waitUntilElementIsVisibleAndClickable(checkBox, "monsterCheckboxNotFound");

        // To avoid stale element exception
        try {
            driver.findElement(checkBox).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            driver.findElement(checkBox).click();
        }
        waitUntilLoadingIconIsNotVisible();
        waitUntilSearchMainContainerLoaded();
    }

    /**
     * verifyCheckboxFilterIsShown
     * Verifies filter is shown in search page
     *
     * @param filterName filter name to check
     * @return boolean result of verification
     */
    public boolean verifyCheckboxFilterIsShown(String filterName) {
        By filter = By.xpath("//div[@class = 'slctd-fltr-item']/span[text() = '" + filterName + "']");
        return customWait.waitUntilElementIsVisibleAndClickable(filter, "brandCheckboxNotFound");
    }

    /**
     * clickOnPrices
     * Clicks on prices menu to open price range options in search page
     */
    public void clickOnPrices() {
        customWait.waitUntilElementIsVisibleAndClickable(pricesDiv, "pricesAreNotReachable");

        // To avoid stale element exception
        try {
            driver.findElement(pricesDiv).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            driver.findElement(pricesDiv).click();
        }
    }

    /**
     * clickOnSearchInPrices
     * Clicks on search button in prices section
     */
    public void clickOnSearchInPrices() {
        customWait.waitUntilElementIsVisibleAndClickable(clickOnSearchInPrices, "searchButtonInPricesAreNotReachable");

        // To avoid stale element exception
        try {
            driver.findElement(clickOnSearchInPrices).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            driver.findElement(clickOnSearchInPrices).click();
        }
        waitUntilLoadingIconIsNotVisible();
        waitUntilSearchMainContainerLoaded();
    }

    /**
     * inputMinPrice
     * Inputs a price value to min price limit in prices section
     */
    public void inputMinPrice(int price) {
        customWait.waitUntilElementIsVisibleAndClickable(minPriceInput, "minPriceInputReachable");
        driver.findElement(minPriceInput).sendKeys(price + "");
    }

    /**
     * inputMaxPrice
     * Inputs a price value to max price limit in prices section
     */
    public void inputMaxPrice(int price) {
        customWait.waitUntilElementIsVisibleAndClickable(maxPriceInput, "maxPriceInputReachable");
        driver.findElement(maxPriceInput).sendKeys(price + "");
    }

    /**
     * inputPriceLimitsAndMakeSearch
     * Inputs a price value to max price limit and min price limit in prices section
     *
     * @param lowestPrice  low bound value of price range
     * @param highestPrice high bound value of price range
     */
    public void inputPriceLimitsAndMakeSearch(int lowestPrice, int highestPrice) {
        clickOnPrices();
        inputMinPrice(lowestPrice);
        inputMaxPrice(highestPrice);
        clickOnSearchInPrices();
    }

    /**
     * clickOnAddToFavorite
     * Clicks on add to favorites button of first found product
     */
    public void clickOnAddToFavorite() {
        customWait.waitUntilElementIsVisibleAndClickable(favoriteButton, "favoriteButtonInSearchPageIsNotReachable");
        clickBody();

        // To avoid stale element exception
        try {
            driver.findElement(favoriteButton).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            customWait.waitUntilElementIsVisibleAndClickable(favoriteButton, "favoriteButtonInSearchPageIsNotReachable");
            driver.findElement(favoriteButton).click();
        }
    }

    /**
     * clickOnRandomProduct
     * Clicks on random (first found) products
     */
    public void clickOnRandomProduct() {
        customWait.waitUntilElementIsVisibleAndClickable(randomProduct, "randomProductNotFound");

        // To avoid stale element exception
        try {
            driver.findElement(randomProduct).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            customWait.waitUntilElementIsVisibleAndClickable(randomProduct, "randomProductNotFound");
            driver.findElement(randomProduct).click();
        }
    }

    /**
     * waitUntilSearchMainContainerLoaded
     * Waits until search page main container div is loaded
     */
    public void waitUntilSearchMainContainerLoaded() {
        customWait.waitUntilElementIsVisibleAndClickable(productCenterWrapper, "productsMainContainerNotFound");
    }

    /**
     * switchToProductPage
     * Switches driver to newly opened tab which is opened by clicking product
     */
    public void switchToProductPage() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
}
