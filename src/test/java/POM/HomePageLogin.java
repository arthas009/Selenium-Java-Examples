package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

/**
 * HomePageLogin
 * This class is used to keep functions and locators about web elements on login window
 */
public class HomePageLogin extends HomePage {
    By logInButton = By.xpath("//button[@type = 'submit']");
    By emailInput = By.id("login-email");
    By passwordInput = By.id("login-password-input");
    By invalidEmailWarning = By.xpath("//span[text() = 'Lütfen geçerli bir e-posta adresi giriniz.' and @class = 'message']");
    By emptyPasswordWarning = By.xpath("//span[text() = 'Lütfen şifrenizi giriniz.' and @class = 'message']");
    By wrongPasswordOrMailWarning = By.xpath("//span[text() = 'E-posta adresiniz ve/veya şifreniz hatalı.' and @class = 'message']");

    /**
     * Constructor of HomePageLogin
     *
     * @param driver: Driver instance to keep
     */
    public HomePageLogin(WebDriver driver) {
        super(driver);
    }

    /**
     * clickLogIn
     * Clicks on Login button
     */
    public void clickLogIn() {
        customWait.waitUntilElementIsVisibleAndClickable(logInButton, "loginButtonNotFound");

        // To avoid stale element exception
        try {
            driver.findElement(logInButton).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            driver.findElement(logInButton).click();
        }
    }

    /**
     * inputLoginEmail
     * Inputs text to e-mail input element
     *
     * @param email text to input
     */
    public void inputLoginEmail(String email) {
        customWait.waitUntilElementIsVisible(emailInput, "loginEmailInputNotFound");
        driver.findElement(emailInput).sendKeys(email);
    }

    /**
     * inputLoginPassword
     * Inputs password to password input element
     *
     * @param password text to input
     */
    public void inputLoginPassword(String password) {
        customWait.waitUntilElementIsVisible(passwordInput, "loginPasswordInputNotFound");
        driver.findElement(passwordInput).sendKeys(password);
    }

    /**
     * checkInvalidEmailWarningIsShown
     * Checks invalid e-mail warning is shown after an invalid e mail input is given and login button is clicked
     */
    public boolean checkInvalidEmailWarningIsShown() {
        return customWait.waitUntilElementIsVisible(invalidEmailWarning, "emailWarningIsNotShown");
    }

    /**
     * checkEmptyPasswordWarningIsShown
     * Checks empty password warning is shown after an empty password input is given and login button is clicked
     */
    public boolean checkEmptyPasswordWarningIsShown() {
        return customWait.waitUntilElementIsVisible(emptyPasswordWarning, "emptyPasswordWarningIsNotShown");
    }

    /**
     * checkWrongPasswordOrMailWarningIsShown
     * Checks invalid username or password error is shown or not.
     */
    public boolean checkWrongPasswordOrMailWarningIsShown() {
        return customWait.waitUntilElementIsVisible(wrongPasswordOrMailWarning, "wrongPasswordOrEmailWarningIsNotShown");
    }

    /**
     * performLogin
     * Performs end-to-end login.
     *
     * @param username Username to login.
     * @param password Password of username to login.
     * @return Returns true if 'Hesabım' hover menu is visible.
     */
    public boolean performLogin(String username, String password) {
        clickOnLogIn();
        inputLoginEmail(username);
        inputLoginPassword(password);
        clickLogIn();
        waitUntilLoadingIconIsNotVisible();
        checkHoverMyAccountIsVisible();
        return checkHoverMyAccountIsVisible();
    }

}
