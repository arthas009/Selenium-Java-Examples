package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

/**
 * FavoritesPage
 * Class to keep locators and methods for Favorites Page
 */
public class FavoritesPage extends HomePage {
    By addToBasket = By.xpath("//span[text() = 'Sepete Ekle' and @class = 'basket-text ']/..");
    By favoriteItem = By.xpath("//div[@class = 'p-card-wrppr']");

    /**
     * Constructor of FavoritesPage
     *
     * @param driver: Driver instance to keep
     */
    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddToBasket() {
        customWait.waitUntilElementIsVisibleAndClickable(addToBasket, "addToBasketButtonNotFound");
        driver.findElement(addToBasket).click();
    }

    /**
     * verifyFavoritesIsNotEmpty
     * Waits until at least one element is present in favorites page
     */
    public boolean verifyFavoritesIsNotEmpty() {
        return customWait.waitUntilElementIsVisibleAndClickable(favoriteItem, "itemInBasketButtonNotFound");
    }

    /**
     * remoteItemFromFavorites
     * Removes an item from favorites page
     *
     * @param index: Index of item to remove
     */
    public void remoteItemFromFavorites(int index) {
        clickBody();
        By removeItemFromFavorites = By.xpath("(//div[@class = 'ufvrt-btn-wrppr'])[" + index + "]");
        if (customWait.waitUntilElementIsVisibleAndClickable(removeItemFromFavorites, "itemInBasketButtonNotFound")) {
            try {
                driver.findElement(removeItemFromFavorites).click();
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                driver.findElement(removeItemFromFavorites).click();
            }
        }
    }
}