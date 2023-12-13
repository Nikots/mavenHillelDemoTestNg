package hillel.ui.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {
    public static String takeScreenshot(WebDriver driver, String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String filePath = "screenshots/" + fileName + System.currentTimeMillis() + ".png";
        File destinationFile = new File(filePath);
        try {
            FileUtils.copyFile(screenshot, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
