package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

/**
 * CustomFirefoxDriver
 * This class is used when cross-browser tests are required. Check configuration .yaml file and Test suite setup to
 * see how it works.
 */
public class CustomFirefoxDriver {
    WebDriver customDriver;

    /**
     * Constructor for CustomFirefoxDriver
     * Sets required options for custom firefox web driver.
     */
    public CustomFirefoxDriver(String binaryPath) {
        System.setProperty("webdriver.gecko.driver", "BrowserDrivers/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        FirefoxBinary binary = new FirefoxBinary(new File(binaryPath));
        options.setBinary(binary);
        customDriver = new FirefoxDriver(options);
        customDriver.manage().window().maximize();
    }

    /**
     * getDriver
     * Gets the custom firefox driver
     *
     * @return web driver instance
     */
    public WebDriver getDriver() {
        return this.customDriver;
    }
}
