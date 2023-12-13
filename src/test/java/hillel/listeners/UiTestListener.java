package hillel.listeners;

import hillel.ui.utils.ScreenshotHelper;
import hillel.utils.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class UiTestListener implements ITestListener {
    public final String DOWNLOAD_FOLDER_PATH = "target/downloads";
    private static final Logger logger = LoggerFactory.getLogger(UiTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: " + result.getMethod().getMethodName());

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", new File(DOWNLOAD_FOLDER_PATH).getAbsolutePath());
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1440, 1100));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        WebDriverProvider.setDriver(driver);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed successfully: " + result.getMethod().getMethodName());
        closeDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getMethod().getMethodName());
        WebDriver driver = WebDriverProvider.getDriver();
        if (driver != null) {
            String screenshotPath = ScreenshotHelper.takeScreenshot(driver, result.getMethod().getMethodName());
            logger.error("Screenshot taken: " + screenshotPath);
        }
        closeDriver();
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Тест skipped: " + result.getMethod().getMethodName());
    }

    public void closeDriver() {
        if (WebDriverProvider.getDriver() != null) {
            WebDriverProvider.getDriver().quit();
            WebDriverProvider.removeDriver();
        }

    }
}
