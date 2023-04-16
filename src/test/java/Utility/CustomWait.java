package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

import java.time.Duration;

/**
 * Test Class CustomWait
 * Being used to perform wait operations on elements
 */
public class CustomWait {
    static ScreenshotHandler screenshotHandler;
    WebDriver driver;
    FluentWait<WebDriver> wait;

    /**
     * Constructor for CustomWait
     *
     * @param driver: WebDriver instance for selenium usage
     */
    public CustomWait(WebDriver driver) {
        this.driver = driver;

        if (screenshotHandler == null)
            this.screenshotHandler = new ScreenshotHandler();

        wait = new FluentWait<>(this.driver);
        wait.withTimeout(Duration.ofMillis(10000));
        wait.pollingEvery(Duration.ofMillis(1000));
        wait.ignoring(NoSuchElementException.class);
    }

    /**
     * waitUntilElementIsVisible
     * Waits until element is visible and reports an error screenshot in failure.
     *
     * @param element:   Element to wait.
     * @param errorText: Error image filename to screenshot.
     */
    public boolean waitUntilElementIsVisible(By element, String errorText) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            screenshotHandler.screenshotError(driver, errorText + ".png");
            Reporter.log("Element " + element.toString() + " not visible after 10 seconds");
            return false;
        }
    }

    /**
     * waitUntilElementIsVisibleAndClickable
     * Waits until element is visible and clickable. Reports an error screenshot in failure.
     *
     * @param element:   Element to wait.
     * @param errorText: Error image filename to screenshot.
     */
    public boolean waitUntilElementIsVisibleAndClickable(By element, String errorText) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            screenshotHandler.screenshotError(driver, errorText + ".png");
            Reporter.log("Element " + element.toString() + " not visible or clickable after 10 seconds");
            return false;
        }
    }

    /**
     * waitUntilElementIsInvisible
     * Waits until element is invisible and reports an error screenshot in failure.
     *
     * @param element:   Element to wait.
     * @param errorText: Error image filename to screenshot.
     */
    public boolean waitUntilElementIsInvisible(By element, String errorText) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            screenshotHandler.screenshotError(driver, errorText + ".png");
            Reporter.log("Element " + element.toString() + " still visible or clickable after 10 seconds");
            return false;
        }
    }
}
