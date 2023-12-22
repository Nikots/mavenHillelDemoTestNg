package hillel.utils;

import hillel.ui.pages.lesson22.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseUiProviderTest {

    public final String DOWNLOAD_FOLDER_PATH = "target/downloads";

    @BeforeMethod
    public void setup() throws MalformedURLException {
        WebDriverManager.chromiumdriver().setup();
        ChromeOptions options = new ChromeOptions();
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("download.default_directory", new File(DOWNLOAD_FOLDER_PATH).getAbsolutePath());
//        options.setExperimentalOption("prefs", prefs);
       // options.addArguments("--headless", "--window-size=1920,1200");
      //  WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

        options.setBinary("/snap/bin/chromium");
        WebDriver driver = new ChromeDriver((options));

        driver.manage().window().setSize(new Dimension(1440, 1100));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();
//        driver.manage().window().setSize(new Dimension(1440, 1100));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        WebDriverProvider.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
       closeDriver();
    }

    public MainPage openMainPage() {
        return new MainPage(WebDriverProvider.getDriver());
    }

    public void closeDriver() {
        if (WebDriverProvider.getDriver() != null) {
            WebDriverProvider.getDriver().quit();
            WebDriverProvider.removeDriver();
        }

    }

}
