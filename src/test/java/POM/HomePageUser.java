package POM;

import org.openqa.selenium.*;
import org.testng.Reporter;

/**
 * HomePageUser
 * This class is used to keep functions and locators about web elements after user logged in
 */
public class HomePageUser extends HomePage {
    By searchButton = By.xpath("//*[@data-testid = 'search-icon']");
    By searchBar = By.xpath("//input[@data-testid = 'suggestion']");
    By hoverMyAccount = By.xpath("//div[@class = 'account-nav-item user-login-container']");
    By logoutButtonInHoverMenu = By.xpath("//button[@class = 'loggedin-account-item']");
    By favoredProductContainer = By.xpath("//div[@class = 'favored-product-container']");
    By productCenterContainer = By.xpath("//div[@class = 'prdct-cntnr-wrppr']");

    /**
     * Constructor of HomePageUser
     *
     * @param driver: Driver instance to keep
     */
    public HomePageUser(WebDriver driver) {
        super(driver);
    }

    /**
     * clickOnSearch
     * Clicks on the search input
     */
    public void clickOnSearch() {
        customWait.waitUntilElementIsVisibleAndClickable(searchButton, "searchButtonNotFound");
        WebElement searchElement = driver.findElement(searchButton);
        searchElement.click();
    }

    /**
     * inputSearchBar
     * Inputs a text into searchbar
     *
     * @param text: text to input
     */
    public void inputSearchBar(String text) {
        customWait.waitUntilElementIsVisibleAndClickable(searchBar, "searchBarNotFound");
        WebElement searchBarElement = driver.findElement(searchBar);

        //Avoid Stale Element Exception in any case
        try {
            searchBarElement.click();
            searchBarElement.sendKeys(text);
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            searchBarElement.click();
            searchBarElement.sendKeys(text);
        }
    }

    /**
     * clickOnTab
     * Clicks on a Tab in trendyol main page
     *
     * @param tabName: Text of tab to click
     */
    public void clickOnTab(String tabName) {
        waitUntilLoadingIconIsNotVisible();
        By tab = By.xpath("//a[text() = '" + tabName + "' and @class = 'category-header']");
        customWait.waitUntilElementIsVisibleAndClickable(tab, "clickableTabNotFoundFound");
        WebElement TabElement = driver.findElement(tab);
        clickBody();

        //Avoid Stale Element Exception in any case
        try {
            TabElement.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            Reporter.log("Exception while clicking to tab " + tabName);
            customWait.waitUntilElementIsVisibleAndClickable(tab, "clickableTabNotFoundFound");
            TabElement.click();
        }
        waitUntilLoadingIconIsNotVisible();
    }

    /**
     * clickOnFirstComponent
     * Clicks on the first component of current tab
     */
    public void clickOnComponent(int index) {
        By firstComponentOfTabs = By.xpath("//div[@class = 'styles-module_slider__o0fqa']/a[" + index + "]");
        customWait.waitUntilElementIsVisibleAndClickable(firstComponentOfTabs, "firstComponentNotFound");
        WebElement firstComponentTabElement = driver.findElement(firstComponentOfTabs);

        //Avoid Stale Element Exception in any case
        try {
            firstComponentTabElement.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            Reporter.log("Exception while clicking to first component in tab");
            customWait.waitUntilElementIsVisibleAndClickable(firstComponentOfTabs, "firstComponentNotFound");
            firstComponentTabElement.click();
        }
        waitUntilLoadingIconIsNotVisible();
    }

    /**
     * verifyFirstFourProductImageIsShown
     * Verifies first 4 product image is shown. First checks favored-product-container is visible.
     * If not, checks
     */
    public void verifyFirstFourProductImageIsShown(String tabName) {
        clickBody();
        if (customWait.waitUntilElementIsVisible(favoredProductContainer, "")) {
            WebElement firstComponentTabElement = driver.findElement(favoredProductContainer);
            for (int i = 1; i <= 4; i++) {
                WebElement childImage = firstComponentTabElement.findElement(By.xpath("//div[" + i + "]/a[1]/img"));
                if (imageExists(childImage)) {
                    System.out.println("Image loaded correctly for " + i + ". product in " + tabName + " tab");
                    Reporter.log("Image loaded correctly for " + i + ". product in " + tabName + " tab", 5);
                } else {
                    System.out.println("Image is not loaded correctly for " + i + ". product in " + tabName + " tab");
                    Reporter.log("Image is not loaded correctly for " + i + ". product in " + tabName + " tab", 5);
                }
            }
        } else {
            WebElement firstComponentTabElement = driver.findElement(productCenterContainer);
            for (int i = 2; i <= 5; i++) {
                WebElement childImage = firstComponentTabElement.findElement(By.xpath("//div[" + i + "]/div[1]/a[1]/div[1]/div[1]/img"));
                if (imageExists(childImage)) {
                    System.out.println("Image loaded correctly for " + (i - 1) + ". product in " + tabName + " tab");
                    Reporter.log("Image loaded correctly for " + (i - 1) + ". product in " + tabName + " tab", 5);
                } else {
                    System.out.println("Image is not loaded correctly for " + (i - 1) + ". product in " + tabName + " tab");
                    Reporter.log("Image is not loaded correctly for " + (i - 1) + ". product in " + tabName + " tab", 5);
                }
            }
        }
    }

    /**
     * imageExists
     * <p>
     * Checks the element has a valid image or not
     */
    public boolean imageExists(WebElement image) {
        return !image.getAttribute("naturalWidth").equals("0");
    }

    /**
     * searchSomethingInSearchbar
     * Inputs a text into searchbar and performs search
     *
     * @param text: text to search on searchbar
     */
    public void searchSomethingInSearchbar(String text) {
        inputSearchBar(text);
        waitUntilLoadingIconIsNotVisible();
        clickOnSearch();
        waitUntilLoadingIconIsNotVisible();
        Reporter.log("Searched " + text + "in searchbar", 1);
    }

    /**
     * logOut
     * Logs out from trendyol by hovering on My Account menu and clicking Log Out button
     */
    public void logOut() {
        hoverOnElement(hoverMyAccount, logoutButtonInHoverMenu);
    }
}
