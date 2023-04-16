package POM;

import Utility.OutlookHandler;
import org.openqa.selenium.*;
import org.testng.Reporter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * HomePageRegister
 * This class is used to keep functions and locators about web elements on main page and register window
 */
public class HomePageRegister extends HomePage {
    OutlookHandler outlookHandler;
    By registrationTabButton = By.xpath("//button[@class = 'q-secondary q-button-medium q-button tab button right ']");
    By verificationKeyAcceptButton = By.xpath("//button[text() = 'Onayla']");
    By maleButton = By.xpath("//button[@class = 'q-gray q-fluid q-button-medium q-button male  ']");
    By femaleButton = By.xpath("//button[@class = 'q-gray q-fluid q-button-medium q-button female   border-right-none']");
    By closeVerificationWindowButton = By.xpath(("//div[@class = 'ty-modal-content ty-relative']/a"));
    By registerButton = By.xpath("//button[@class = 'q-primary q-fluid q-button-medium q-button submit']");
    By emailVerificationInput = By.xpath("//input[@class = 'ty-bg-beige ty-input ty-textbox ty-bordered ty-input-small']");
    By registerEmailInputInput = By.id("register-email");
    By registerPasswordInput = By.id("register-password-input");
    By personalDataCheckbox = By.xpath("//div[@class = 'personal-checkbox']/div/div/div");
    By wrongPasswordIncludeNumberWarning = By.xpath("//span[text() = 'Şifreniz en az 1 rakam içermelidir.' and @class = 'message']");
    By wrongPasswordLengthWarning = By.xpath("//span[text() = 'Şifreniz 7 ile 15 karakter arasında olmalıdır.' and @class = 'message']");
    By existingEmailWarning = By.xpath("//span[text() = 'Bu e-posta adresi kullanılamaz. Lütfen başka bir e-posta adresi deneyiniz.' and @class = 'message']");
    By wrongEmailFormatWarning = By.xpath("//span[text() = 'Lütfen geçerli bir email adresi giriniz.' and @class = 'message']");
    By emptyEmailOrPasswordWarning = By.xpath("//span[text() = 'E-posta ve şifrenizi giriniz.' and @class = 'message']");

    /**
     * Constructor of HomePageRegister
     * Creates a new outlook handler
     *
     * @param driver: Driver instance to keep
     */
    public HomePageRegister(WebDriver driver) {
        super(driver);
        outlookHandler = new OutlookHandler();
    }

    /**
     * openRegistrationSubTab
     * Clicks on the register tab button in login page.
     */
    public void openRegistrationSubTab() {
        customWait.waitUntilElementIsVisibleAndClickable(registrationTabButton, "registrationSubTabButtonNotFound");
        WebElement registrationTabElement = driver.findElement(registrationTabButton);

        //Avoid Stale Element Exception in any case
        try {
            registrationTabElement.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            registrationTabElement.click();
        }
    }

    /**
     * inputRegistrationEmail
     * Inputs given string to registration e-mail input
     *
     * @param email: E-mail to input
     */
    public void inputRegistrationEmail(String email) {
        customWait.waitUntilElementIsVisibleAndClickable(registerEmailInputInput, "registrationEmailInputNotFound");
        WebElement registrationEmailInputElement = driver.findElement(registerEmailInputInput);
        registrationEmailInputElement.sendKeys(email);
    }

    /**
     * inputRegistrationPassword
     * Inputs given string to registration password input
     *
     * @param password: Password to input
     */
    public void inputRegistrationPassword(String password) {
        customWait.waitUntilElementIsVisibleAndClickable(registerPasswordInput, "registrationPasswordInputNotFound");
        WebElement registrationPasswordInputElement = driver.findElement(registerPasswordInput);
        registrationPasswordInputElement.sendKeys(password);
    }

    /**
     * clickOnMaleButton
     * Clicks on the male button in registration page.
     */
    public void clickOnMaleButton() {
        customWait.waitUntilElementIsVisibleAndClickable(maleButton, "maleButtonNotFound");
        WebElement maleButtonElement = driver.findElement(maleButton);

        //Avoid Stale Element Exception in any case
        try {
            maleButtonElement.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            maleButtonElement.click();
        }
    }

    /**
     * clickOnFemaleButton
     * Clicks on the female button in registration page.
     */
    public void clickOnFemaleButton() {
        customWait.waitUntilElementIsVisibleAndClickable(femaleButton, "femaleButtonNotFound");
        WebElement femaleButtonElement = driver.findElement(femaleButton);

        //Avoid Stale Element Exception in any case
        try {
            femaleButtonElement.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            femaleButtonElement.click();
        }
    }

    /**
     * clickOnPersonalDataAcceptCheckbox
     * Clicks on personal data acceptance checkbox.
     */
    public void clickOnPersonalDataAcceptCheckbox() {
        customWait.waitUntilElementIsVisibleAndClickable(personalDataCheckbox, "personalDataCheckboxNotFound");
        WebElement personalDataCheckboxElement = driver.findElement(personalDataCheckbox);

        //Avoid Stale Element Exception in any case
        try {
            personalDataCheckboxElement.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            personalDataCheckboxElement.click();
        }
    }

    /**
     * clickOnRegisterButton
     * Clicks on the register button in registration page.
     */
    public void clickOnRegisterButton() {
        customWait.waitUntilElementIsVisibleAndClickable(registerButton, "registerButtonNotFound");
        WebElement registerButtonElement = driver.findElement(registerButton);

        //Avoid Stale Element Exception in any case
        try {
            registerButtonElement.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            registerButtonElement.click();
        }
    }

    /**
     * clickOnVerificationKeyAcceptButton
     * Clicks on accept button in e-mail verification window
     */
    public void clickOnVerificationKeyAcceptButton() {
        if (!customWait.waitUntilElementIsVisibleAndClickable(verificationKeyAcceptButton, "")) {
            Reporter.log("Verification code accept button is not shown or clickable", 5);
            System.out.println("Verification code accept button is not shown or clickable");
            return;
        }
        WebElement verificationKeyAcceptButtonElement = driver.findElement(verificationKeyAcceptButton);

        //Avoid Stale Element Exception in any case
        try {
            verificationKeyAcceptButtonElement.click();
            System.out.println("Verification accept clicked");
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            Reporter.log("An exception occured while clicking on Verification code accept button", 5);
            System.out.println("An exception occured while clicking on Verification code accept button");
            verificationKeyAcceptButtonElement.click();
        }
    }

    /**
     * checkWrongPasswordIncludeNumberWarningIsShown
     * Checks wrong password format warning is shown after a correct mail and wrong password (including only letters) input is given,
     * personal data checkbox is clicked and register button is clicked.
     */
    public boolean checkWrongPasswordIncludeNumberWarningIsShown() {
        return customWait.waitUntilElementIsVisible(wrongPasswordIncludeNumberWarning, "wrongPasswordIncludeNumberWarningIsNotShown");
    }

    /**
     * checkWrongPasswordLengthWarningIsShown
     * Checks wrong password length warning is shown after a correct mail and wrong password input by length is given,
     * personal data checkbox is clicked and register button is clicked.
     */
    public boolean checkWrongPasswordLengthWarningIsShown() {
        return customWait.waitUntilElementIsVisible(wrongPasswordLengthWarning, "wrongPasswordLengthWarningIsNotShown");
    }

    /**
     * checkWrongEmailFormatWarningIsShown
     * Checks wrong e-mail format warning is shown after a wrong mail and correct password input is given,
     * personal data checkbox is clicked and register button is clicked.
     */
    public boolean checkWrongEmailFormatWarningIsShown() {
        if (!customWait.waitUntilElementIsVisible(wrongEmailFormatWarning, "wrongEmailFormatWarningIsNotShown")) {
            clickOnRegisterButton();
            return customWait.waitUntilElementIsVisible(wrongEmailFormatWarning, "wrongEmailFormatWarningIsNotShown");
        } else {
            return true;
        }
    }

    /**
     * checkEmptyEmailOrPasswordWarningIsShown
     * Checks empty e-mail or password warning is shown after an empty mail and correct password input is given,
     * personal data checkbox is clicked and register button is clicked.
     */
    public boolean checkEmptyEmailOrPasswordWarningIsShown() {
        if (!customWait.waitUntilElementIsVisible(emptyEmailOrPasswordWarning, "emptyEmailOrPasswordWarningIsNotShown")) {
            clickOnRegisterButton();
            return customWait.waitUntilElementIsVisible(emptyEmailOrPasswordWarning, "emptyEmailOrPasswordWarningIsNotShown");
        } else {
            return true;
        }
    }

    /**
     * checkEmailVerificationInputIsNotShown
     * Checks email verification code input is not shown
     */
    public boolean checkEmailVerificationInputIsNotShown() {
        return !customWait.waitUntilElementIsVisible(emailVerificationInput, "");
    }

    /**
     * findAndInputEmailVerificationCode
     * Logs in to given e-mail, retrieves the Trendyol e-mail verification code from mails and inputs it to verification code input.
     *
     * @param emailToLogIn:    E-mail address. Currently only supports @outlook.com.
     * @param passwordOfEmail: Password of given E-mail address.
     */
    public void findAndInputEmailVerificationCode(String emailToLogIn, String passwordOfEmail) {
        if (!customWait.waitUntilElementIsVisibleAndClickable(emailVerificationInput, "emailVerificationInputNotFound")) {
            return;
        }
        WebElement registrationPasswordInputElement = driver.findElement(emailVerificationInput);
        String[] verificationCode = outlookHandler.startSessionAndGetVerificationCode(emailToLogIn, passwordOfEmail);

        // If verification code is null, close verification window
        if (verificationCode == null) {
            driver.findElement(closeVerificationWindowButton).click();
            return;
        }
        StringBuilder codeToInput = new StringBuilder();
        for (int i = 0; i < verificationCode.length; i++) {
            codeToInput.append(verificationCode[i]);
        }
        try {
            // Copy verification code to clipboard
            StringSelection selection = new StringSelection(codeToInput.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);

            // Do Ctrl + v to verification input
            registrationPasswordInputElement.click();
            registrationPasswordInputElement.sendKeys(Keys.CONTROL + "v");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * performRegistrationAndReturnResult
     * Performs end-to-end register. Decision mechanism is ending by checking 'Hesabım' text is finally shown or not at the end of registration.
     *
     * @param username Username to register.
     * @param password Password of username to register.
     * @return Returns true if 'Hesabım' hover menu is visible
     */
    public boolean performRegistrationAndReturnResult(String username, String password, String passwordOfEmail) {
        clickOnLogIn();
        openRegistrationSubTab();
        inputRegistrationEmail(username);
        inputRegistrationPassword(password);
        clickOnMaleButton();
        clickOnPersonalDataAcceptCheckbox();
        clickOnRegisterButton();
        findAndInputEmailVerificationCode(username, passwordOfEmail);
        clickOnVerificationKeyAcceptButton();
        System.out.println("Registering completed. Checiking 'Hesabım' text now..");
        Reporter.log("Registering completed. Checiking 'Hesabım' text now..", 3);
        clickOnTrendyolIcon();

        //Check 'Hesabım' text is visible or not, then return
        return checkHoverMyAccountIsVisible();
    }

    /**
     * performRegistrationWithoutCheckingPersonalDataAccept
     * Performs end-to-end register. Decision mechanism is ending by checking e-mail verification input is shown or not.
     *
     * @param username Username to register.
     * @param password Password of username to register.
     * @return Returns true if  e-mail verification input hover menu is not visible
     */
    public boolean performRegistrationWithoutCheckingPersonalDataAccept(String username, String password) {
        clickOnLogIn();
        openRegistrationSubTab();
        inputRegistrationEmail(username);
        inputRegistrationPassword(password);
        clickOnFemaleButton();
        clickOnRegisterButton();

        //Check e-mail verification input is not shown
        return checkEmailVerificationInputIsNotShown();
    }

    /**
     * performRegistrationAndExpectFailure
     * Performs end-to-end register. Decision mechanism is ending by checking e-mail verification input is shown or not.
     *
     * @param username Username to register.
     * @param password Password of username to register.
     * @return Returns true if e-mail verification input hover menu is not visible
     */
    public boolean performRegistrationAndExpectFailure(String username, String password) {
        clickOnLogIn();
        openRegistrationSubTab();
        inputRegistrationEmail(username);
        inputRegistrationPassword(password);
        clickOnFemaleButton();
        clickOnPersonalDataAcceptCheckbox();
        clickOnRegisterButton();

        //Check e-mail verification input is not shown
        return checkEmailVerificationInputIsNotShown();
    }
}
