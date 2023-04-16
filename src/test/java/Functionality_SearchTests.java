import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test Class Functionality_SearchTests
 * Being used to execute tests based on searching on Trendyol
 */
public class Functionality_SearchTests extends BaseClass {

    @Test(priority = 2, description = "Searchs Monster products under 3000 - 10000 TL limit and adds first product to basket." +
            "Verifies brand selection in search page is working." +
            "Verifies price range selection in search page is working." +
            "Verifies adding to basket in product page is working.")
    public static void Verify_MonsterProduct_Case() {
        if (!homePageLogin.performLogin(getTestingUsername(), getTestingPassword())) {
            Reporter.log("Login has failed. Terminating test case");
            Assert.fail("Login has failed. Terminating test case");
        }
        homePageUser.searchSomethingInSearchbar("Oyuncu Bilgisayarı");
        searchPage.clickOnBrandCheckbox("Monster");
        Assert.assertTrue(searchPage.verifyCheckboxFilterIsShown("Monster"));
        searchPage.inputPriceLimitsAndMakeSearch(3000, 10000);
        Assert.assertTrue(searchPage.verifyCheckboxFilterIsShown("3000 TL - 10000 TL"));
        searchPage.clickOnRandomProduct();
        searchPage.switchToProductPage();
        productPage.clickOnAddToBasket();
        productPage.closeProductBrowserTab();
        homePageUser.goToMyBasketTab();
        Assert.assertTrue(basketPage.verifyBasketIsNotEmptyAndDeleteItem(1));
        favoritesPage.clickOnTrendyolIcon();
    }

    @Test(priority = 3, description = "Searchs a random Gomlek product and adds to favorite and basket pages." +
            "Verifies adding to favorite in search page is working." +
            "Verifies adding to basket from favorites page is working.")
    public static void Verify_GomlekProduct_Case() {
        if (!homePageLogin.performLogin(getTestingUsername(), getTestingPassword())) {
            Reporter.log("Login has failed. Terminating test case");
            Assert.fail("Login has failed. Terminating test case");
        }
        homePageUser.searchSomethingInSearchbar("Gömlek");
        searchPage.clickOnAddToFavorite();
        searchPage.goToFavoritesTab();
        Assert.assertTrue(favoritesPage.verifyFavoritesIsNotEmpty());
        favoritesPage.clickOnAddToBasket();
        favoritesPage.remoteItemFromFavorites(1);
        homePageUser.goToMyBasketTab();
        Assert.assertTrue(basketPage.verifyBasketIsNotEmptyAndDeleteItem(1));
        favoritesPage.clickOnTrendyolIcon();
    }
}
