package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

/**
 * BasketPage
 * Class to keep locators and methods for Basket Page
 */
public class BasketPage extends HomePage {
    By deleteButton = By.xpath("//button[text() = 'Sil']");

    /**
     * Constructor of BasketPage
     *
     * @param driver: Driver instance to keep
     */
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    /**
     * verifyBasketIsNotEmptyAndDeleteItem
     * Verifies basket page is not empty and removes a given item
     *
     * @param index: Index of item to remove
     */
    public boolean verifyBasketIsNotEmptyAndDeleteItem(int index) {
        clickBody();
        By removeItemFromBasket = By.xpath("(//i[@class = 'i-trash'])[" + index + "]/parent::button");
        if (customWait.waitUntilElementIsVisibleAndClickable(removeItemFromBasket, "itemInBasketButtonNotFound")) {
            try {
                driver.findElement(removeItemFromBasket).click();
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                driver.findElement(removeItemFromBasket).click();
            }

            try {
                driver.findElement(deleteButton).click();
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                driver.findElement(deleteButton).click();
            }
            return true;
        } else
            return false;
    }

}
