package hillel.utils;

import hillel.ui.pages.lesson22.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
//        URL gridUrl = new URL("http://localhost:4444/wd/hub"); // Замініть hostname на адресу сервера Selenium Grid
        URL gridUrl = new URL("http://192.168.64.2:4444/wd/hub"); // Замініть hostname на адресу сервера Selenium Grid
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox"); // Можете вибрати firefox, chrome тощо
     //   capabilities.setBrowserName("chrome"); // Можете вибрати firefox, chrome тощо

        WebDriver driver = new RemoteWebDriver(gridUrl, capabilities);

        driver.manage().window().setSize(new Dimension(1440, 1100));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        WebDriverProvider.setDriver(driver);
    }

//    @AfterMethod
//    public void tearDown() {
//       closeDriver();
//    }
//
//    public MainPage openMainPage() {
//        return new MainPage(WebDriverProvider.getDriver());
//    }
//
//    public void closeDriver() {
//        if (WebDriverProvider.getDriver() != null) {
//            WebDriverProvider.getDriver().quit();
//            WebDriverProvider.removeDriver();
//        }
//
//    }

}
