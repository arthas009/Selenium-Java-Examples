import POM.*;
import Utility.CustomChromeDriver;
import Utility.CustomEdgeDriver;
import Utility.CustomFirefoxDriver;
import Utility.YamlReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Repository class BaseClass
 * Being used to keep methods like suite setup, suite teardown or method teardown etc.
 */
public class BaseClass {
    static HomePageLogin homePageLogin;
    static HomePageRegister homePageRegister;
    static HomePageUser homePageUser;
    static SearchPage searchPage;
    static ProductPage productPage;
    static FavoritesPage favoritesPage;
    static BasketPage basketPage;
    private static YamlReader configurationReader;
    private static WebDriver driver;
    private static String originalHandle;
    private static String testingUsername;
    private static String testingPassword;
    private static String registerTestingUsername;
    private static String registerTestingMailPassword;

    /**
     * InitializeBrowser
     * Suite Setup
     * Handles POM pages, configuration reading and web driver initialization for cross browser testing.
     */
    @BeforeSuite
    public static void InitializeBrowser() {
        // Reading configuration from yaml
        configurationReader = new YamlReader("Configuration/configuration.yaml");
        String browserType = configurationReader.getVariable("BROWSER");
        String testingMainURL = configurationReader.getVariable("BASE_URL");
        testingUsername = configurationReader.getVariable("TESTING_USERNAME");
        testingPassword = configurationReader.getVariable("TESTING_PASSWORD");
        registerTestingUsername = configurationReader.getVariable("REGISTER_TESTING_USERNAME");
        registerTestingMailPassword = configurationReader.getVariable("REGISTER_TESTING_MAIL_PASSWORD");

        //Cross browser selection section
        if (browserType.equals("Chrome"))
            driver = new CustomChromeDriver().getDriver();
        else if (browserType.equals("Firefox")) {
            String firefoxBinaryPath = configurationReader.getVariable("FIREFOX_BINARY_PATH");
            driver = new CustomFirefoxDriver(firefoxBinaryPath).getDriver();
        } else if (browserType.equals("Edge"))
            driver = new CustomEdgeDriver().getDriver();

        // Instantiate POM object
        homePageRegister = new HomePageRegister(driver);
        homePageLogin = new HomePageLogin(driver);
        homePageUser = new HomePageUser(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
        favoritesPage = new FavoritesPage(driver);

        // Get to url and accept cookie if needed
        driver.get(testingMainURL);
        originalHandle = driver.getWindowHandle();
        homePageLogin.acceptCookie();
    }

    /**
     * CloseBrowser
     * Suite Teardown
     * Closes browser after all tests are executed.
     */
    @AfterSuite
    public static void CloseBrowser() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            driver.close();
        }
    }

    /**
     * CloseBrowser
     * Method Teardown
     * Closes all browser tabs except first one, then logs out from trendyol
     */
    @AfterMethod
    public static void CaseTeardown() {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(originalHandle);
        homePageUser.clickBody();
        homePageUser.logOut();
        homePageUser.clickOnTrendyolIcon();
    }

    /**
     * getOriginalHandle
     * Gets the original handle of first opened browser tab
     */
    public static String getOriginalHandle() {
        return originalHandle;
    }

    // Following variables are used in test cases.

    public static String getTestingUsername() {
        return testingUsername;
    }

    public static String getTestingPassword() {
        return testingPassword;
    }

    public static String getRegisterTestingUsername() {
        return registerTestingUsername;
    }

    public static String getRegisterTestingMailPassword() {
        return registerTestingMailPassword;
    }

}
