package Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotHandler
 * This class is used when errors needed to be screenshotted.
 */
public class ScreenshotHandler extends TestListenerAdapter {
    String filename;
    String folder_path;
    WebDriver driver;

    /**
     * Constructor for ScreenshotHandler
     * Sets folder path of screenshots
     */
    public ScreenshotHandler() {
        folder_path = YamlReader.getVariable("TEST_OUTPUT_DIRECTORY") + "/Screenshots/";

        File directory = new File(folder_path);

        if (!directory.exists())
            directory.mkdir();

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy-hh mm ss");
        String time = dateFormat.format(now);
        folder_path += "Test - " + time;
        new File(folder_path).mkdir();
    }

    /**
     * screenshotError
     * Screenshots the current page to given test output folder path.
     *
     * @param driver:   Driver instance of web driver
     * @param filename: Filename of screenshot to save
     */
    public void screenshotError(WebDriver driver, String filename) {
        this.filename = filename;
        this.driver = driver;
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        if (!filename.equals(".png")) {
            try {
                FileHandler.copy(source, new File(folder_path + "/" + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
