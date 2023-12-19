package hillel.lesson31_grid_proxy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridTest {
    @Test
    public void test() throws MalformedURLException {
        // Налаштування DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        //  ChromeOptions options = new ChromeOptions();

        // Створення екземпляра RemoteWebDriver
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.101:4444/wd/hub"), capabilities);

        // Тестовий сценарій
        driver.get("https://www.selenium.dev/documentation/grid/getting_started/");
        System.out.println("Title is: " + driver.getTitle());

        // Закриття браузера
        driver.quit();
    }
}
