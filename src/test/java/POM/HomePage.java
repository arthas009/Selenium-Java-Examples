package POM;

import Utility.CustomWait;
import Utility.YamlReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import java.awt.*;

/**
 * HomePage
 * This class is used to keep functions and locators about web elements about main page
 * It also has a default Fluent wait which is used by itself and child classes to wait elements to be visible.
 * Fluent wait has 45 second timeout and polls every second
 */
public class HomePage {
    static CustomWait customWait;
    protected WebDriver driver;
    protected String test_output_directory;
    By cookieAcceptButton = By.id("onetrust-accept-btn-handler");
    By logInButton = By.xpath("//p[text() = 'Giriş Yap' and @class = 'link-text']");
    By myFavorites = By.xpath("//p[text() = 'Favorilerim' and @class = 'link-text']");
    By myBasket = By.xpath("//p[text() = 'Sepetim' and @class = 'link-text']");
    By hoverMyAccount = By.xpath("//div[@class = 'account-nav-item user-login-container']/div/p");
    By trendyolIcon = By.xpath("//img[@alt = 'Trendyol']");
    By loadingIcon = By.xpath("//div[@class = 'q-loader']");

    /**
     * Constructor of HomePage
     *
     * @param driver: Driver instance to keep
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;

        if (customWait == null)
            customWait = new CustomWait(driver);

        test_output_directory = YamlReader.getVariable("TEST_OUTPUT_DIRECTORY");
    }

    /**
     * acceptCookie
     * Clicks on accept button when Accept Cookie window is shown
     */
    public void acceptCookie() {
        customWait.waitUntilElementIsVisibleAndClickable(cookieAcceptButton, "cookieAcceptButtonIsNotReachable");
        driver.findElement(cookieAcceptButton).click();
    }

    /**
     * clickOnLogIn
     * Clicks on Log in button in Home page
     */
    public void clickOnLogIn() {
        customWait.waitUntilElementIsVisibleAndClickable(logInButton, "logInButtonNotFound");
        driver.findElement(logInButton).click();
    }

    /**
     * goToFavoritesTab
     * Clicks on My Favorites button in Home page
     */
    public void goToFavoritesTab() {
        customWait.waitUntilElementIsVisibleAndClickable(myFavorites, "favoritesButtonNotFound");
        clickBody();

        // To avoid stale element exception
        try {
            driver.findElement(myFavorites).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            driver.findElement(myFavorites).click();
        }
    }

    /**
     * goToMyBasketTab
     * Clicks on My Basket button in Home page
     */
    public void goToMyBasketTab() {
        customWait.waitUntilElementIsVisibleAndClickable(myBasket, "myBasketButtonNotFound");
        clickBody();

        // To avoid stale element exception
        try {
            driver.findElement(myBasket).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            driver.findElement(myBasket).click();
        }
    }

    /**
     * clickOnTrendyolIcon
     * Clicks on Trendyol icon
     */
    public void clickOnTrendyolIcon() {
        customWait.waitUntilElementIsVisibleAndClickable(trendyolIcon, "trendyolIconNotReachable");

        //Avoid Stale Element Exception in any case
        try {
            driver.findElement(trendyolIcon).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            driver.findElement(trendyolIcon).click();
        }

    }

    /**
     * waitUntilLoadingIconIsNotVisible
     * Waits until loading circle icon is not shown on web page
     */
    public void waitUntilLoadingIconIsNotVisible() {
        customWait.waitUntilElementIsInvisible(loadingIcon, "loadingIconStillVisible");
    }

    /**
     * checkHoverMyAccountIsVisible
     * Checks an user is logged in.
     */
    public boolean checkHoverMyAccountIsVisible() {
        try {
            customWait.waitUntilElementIsVisible(hoverMyAccount, "myAccountHoverMenuIsNotVisible");
            WebElement myAccountElement = driver.findElement(hoverMyAccount);
            return myAccountElement.getText().equals("Hesabım");

        } catch (TimeoutException e) {
            Reporter.log("Element " + hoverMyAccount.toString() + " not visible");
            return false;
        }
    }

    /**
     * hoverOnElement
     * Hovers on a element, then clicks on a button in hover menu
     *
     * @param hoveringElementXpath xpath of element to hover on
     * @param elementToClickXpath  xpath of hover menu button to click
     */
    public void hoverOnElement(By hoveringElementXpath, By elementToClickXpath) {
        Actions action = new Actions(driver);
        customWait.waitUntilElementIsVisibleAndClickable(hoveringElementXpath, "hoveringButtonNotFound");
        WebElement we = driver.findElement(hoveringElementXpath);
        action.moveToElement(we).perform();
        if (customWait.waitUntilElementIsVisibleAndClickable(elementToClickXpath, "buttonInsideHoverMenuNotFound")) ;
        {
            try {
                WebElement elementToClick = driver.findElement(elementToClickXpath);
                action.moveToElement(elementToClick).perform();
                elementToClick.click();
            } catch (NoSuchElementException e) {
                return;
            }
        }
    }

    /**
     * clickBody
     * Clicks on body element to close randomly popped up windows
     */
    public void clickBody() {
        Actions action = new Actions(driver);
        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.mouseMove(1, 1);
        action.click().build().perform();
    }
}
