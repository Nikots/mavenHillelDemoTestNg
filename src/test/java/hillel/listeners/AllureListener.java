package hillel.listeners;

import hillel.utils.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AllureListener implements TestLifecycleListener {
    public final String DOWNLOAD_FOLDER_PATH = "target/downloads";
    @Override
    public void beforeTestStart(TestResult result) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", new File(DOWNLOAD_FOLDER_PATH).getAbsolutePath());
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1440, 1100));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        WebDriverProvider.setDriver(driver);    }

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            if (WebDriverProvider.getDriver() != null) {
                makeScreenshotOnFailure(WebDriverProvider.getDriver());
            }
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] makeScreenshotOnFailure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void closeDriver() {
        if (WebDriverProvider.getDriver() != null) {
            WebDriverProvider.getDriver().quit();
            WebDriverProvider.removeDriver();
        }
    }

    @Override
    public void afterTestStop(TestResult result) {
        closeDriver();
    }
}
